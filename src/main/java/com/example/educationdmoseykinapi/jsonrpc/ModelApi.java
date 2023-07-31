package com.example.educationdmoseykinapi.jsonrpc;

import com.example.educationdmoseykinapi.dto.model.ModelCard;
import com.example.educationdmoseykinapi.dto.model.ModelRequest;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;

@JsonRpcService(value = "/model")
public interface ModelApi {

    ModelCard save(@JsonRpcParam(value = "request") ModelRequest modelRequest);

    ModelCard fetchById(@JsonRpcParam(value = "id") String id);

    List<ModelCard> getAll();

    ModelCard update(@JsonRpcParam(value = "request") ModelRequest modelRequest);

    void delete(@JsonRpcParam(value = "id") String id);
}
