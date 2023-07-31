package com.example.educationdmoseykinapi.service;

import com.example.educationdmoseykinapi.component.mapper.ModelMapper;
import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import com.example.educationdmoseykinapi.dto.model.ModelCard;
import com.example.educationdmoseykinapi.dto.model.ModelLinkRequest;
import com.example.educationdmoseykinapi.dto.model.ModelLinkResponse;
import com.example.educationdmoseykinapi.dto.model.ModelRequest;
import com.example.educationdmoseykinapi.httpclient.ClassClient;
import com.example.educationdmoseykinapi.httpclient.ModelClient;
import com.example.educationdmoseykinapi.httpclient.ModelLinkClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelClient modelClient;
    private final ModelLinkClient modelLinkClient;
    private final ModelMapper modelMapper;
    private final ClassClient classClient;

    @Override
    public ModelCard save(ModelRequest modelRequest) {

        ModelCard modelCard = modelClient.save(modelRequest);
        String modelId = modelCard.getId();

        try {
            ModelLinkRequest modelLinkRequest = new ModelLinkRequest(
                    modelCard.getId(), modelCard.getName(), modelRequest.getClassMongoId());
            ModelLinkResponse modelLinkResponse = modelLinkClient.save(modelLinkRequest);
            modelCard.setClassInfo(modelLinkResponse.getClassResponse());
            return modelCard;
        } catch (RuntimeException e) {
            modelClient.delete(modelId);
            return new ModelCard();
        }
    }

    @Override
    public ModelCard fetchById(String id) {
        ModelCard modelCard = modelClient.get(id);
        ClassInfo classInfo = classClient.getByRelatedModelMongoId(modelCard.getId());
        modelCard.setClassInfo(classInfo);
        return modelCard;
    }

    @Override
    public List<ModelCard> getAll() {
        List<ModelCard> modelCards = modelClient.getAll();
        List<ModelLinkResponse> modelLinkResponses = modelLinkClient.getAll();
        modelCards.forEach(modelCard -> modelLinkResponses.stream()
                .filter(modelLinkResponse -> modelLinkResponse.getMongoId().equals(modelCard.getId()))
                .findFirst()
                .ifPresent(modelLinkResponse -> modelCard.setClassInfo(modelLinkResponse.getClassResponse())));
        return modelCards;
    }

    @Override
    public ModelCard update(ModelRequest modelRequest) {
        ModelCard modelCardOldVariant = modelClient.get(modelRequest.getId());

        ModelCard updatedModelCard = modelClient.update(modelRequest);

        try {
            ModelLinkRequest modelLinkRequest = new ModelLinkRequest(
                    updatedModelCard.getId(), updatedModelCard.getName(), modelRequest.getClassMongoId());
            ModelLinkResponse modelLinkResponse = modelLinkClient.update(modelLinkRequest);
            updatedModelCard.setClassInfo(modelLinkResponse.getClassResponse());
            return updatedModelCard;
        } catch (RuntimeException e) {
            modelClient.update(modelMapper.toModelRequest(modelCardOldVariant));
            return new ModelCard();
        }
    }

    @Override
    public void delete(String id) {
        ModelCard modelCardToBeDeleted = modelClient.get(id);
        modelClient.delete(id);
        try {
            modelLinkClient.delete(id);
        } catch (RuntimeException e) {
            modelClient.save(modelMapper.toModelRequest(modelCardToBeDeleted));
        }
    }
}
