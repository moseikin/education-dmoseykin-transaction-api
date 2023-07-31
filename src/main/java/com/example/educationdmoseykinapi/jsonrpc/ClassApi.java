package com.example.educationdmoseykinapi.jsonrpc;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import com.example.educationdmoseykinapi.dto.clazz.ClassRequest;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;

@JsonRpcService(value = "/class")
public interface ClassApi {

    ClassInfo save(@JsonRpcParam(value = "request") ClassRequest classRequest);

    ClassInfo getByMongoId(@JsonRpcParam(value = "mongoId") String mongoId);

    List<ClassInfo> getAll();

    ClassInfo update(@JsonRpcParam(value = "request") ClassRequest classRequest);

    void delete(@JsonRpcParam(value = "mongoId") String mongoId);

}
