package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.model.ModelCard;
import com.example.educationdmoseykinapi.dto.model.ModelRequest;

import java.util.List;

public interface ModelClient {

    ModelCard save(ModelRequest modelRequest);

    ModelCard get(String id);

    List<ModelCard> getAll();

    ModelCard update(ModelRequest modelRequest);

    void delete(String id);
}
