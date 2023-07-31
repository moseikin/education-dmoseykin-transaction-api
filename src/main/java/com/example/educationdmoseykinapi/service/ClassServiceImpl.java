package com.example.educationdmoseykinapi.service;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import com.example.educationdmoseykinapi.dto.clazz.ClassRequest;
import com.example.educationdmoseykinapi.httpclient.ClassClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassClient classClient;

    @Override
    public ClassInfo save(ClassRequest classRequest) {
        return classClient.save(classRequest);
    }

    @Override
    public ClassInfo getByMongoId(String mongoId) {
        return classClient.getByMongoId(mongoId);
    }

    @Override
    public List<ClassInfo> getAll() {
        return classClient.getAll();
    }

    @Override
    public ClassInfo update(ClassRequest classRequest) {
        return classClient.update(classRequest);
    }

    @Override
    public void delete(String mongoId) {
        classClient.delete(mongoId);
    }
}
