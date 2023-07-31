package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import com.example.educationdmoseykinapi.dto.clazz.ClassRequest;

import java.util.List;

public interface ClassClient {

    ClassInfo save(ClassRequest classRequest);

    ClassInfo getByMongoId(String mongoId);

    List<ClassInfo> getAll();

    ClassInfo update(ClassRequest classRequest);

    void delete(String mongoId);

    ClassInfo getByRelatedModelMongoId(String modelMongoId);
}
