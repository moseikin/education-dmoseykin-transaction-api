package com.example.educationdmoseykinapi.jsonrpc;

import com.example.educationdmoseykinapi.dto.model.ModelCard;
import com.example.educationdmoseykinapi.dto.model.ModelRequest;
import com.example.educationdmoseykinapi.service.ModelService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@AutoJsonRpcServiceImpl
public class ModelApiImpl implements ModelApi {

    private final ModelService modelService;

    @Override
    public ModelCard save(ModelRequest modelRequest) {
        return modelService.save(modelRequest);
    }

    @Override
    public ModelCard fetchById(String id) {
        return modelService.fetchById(id);
    }

    @Override
    public List<ModelCard> getAll() {
        return modelService.getAll();
    }

    @Override
    public ModelCard update(ModelRequest modelRequest) {
        return modelService.update(modelRequest);
    }

    @Override
    public void delete(String id) {
        modelService.delete(id);
    }
}
