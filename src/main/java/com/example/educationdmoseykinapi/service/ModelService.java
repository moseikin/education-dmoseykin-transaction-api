package com.example.educationdmoseykinapi.service;

import com.example.educationdmoseykinapi.dto.model.ModelCard;
import com.example.educationdmoseykinapi.dto.model.ModelRequest;

import java.util.List;

public interface ModelService {

    ModelCard save(ModelRequest modelRequest);

    ModelCard fetchById(String id);

    List<ModelCard> getAll();

    ModelCard update(ModelRequest modelRequest);

    void delete(String id);
}
