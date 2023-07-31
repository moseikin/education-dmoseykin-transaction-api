package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.model.ModelLinkRequest;
import com.example.educationdmoseykinapi.dto.model.ModelLinkResponse;

import java.util.List;

public interface ModelLinkClient {

    ModelLinkResponse save(ModelLinkRequest modelLinkRequest);

    ModelLinkResponse getByMongoId(String mongoId);

    List<ModelLinkResponse> getAll();

    ModelLinkResponse update(ModelLinkRequest modelLinkRequest);

    void delete(String mongoId);
}
