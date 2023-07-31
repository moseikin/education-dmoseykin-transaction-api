package com.example.educationdmoseykinapi.jsonrpc;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import com.example.educationdmoseykinapi.dto.clazz.ClassRequest;
import com.example.educationdmoseykinapi.service.ClassService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AutoJsonRpcServiceImpl
@RequiredArgsConstructor
public class ClassApiImpl implements ClassApi {

    private final ClassService classService;

    @Override
    public ClassInfo save(ClassRequest classRequest) {
        return classService.save(classRequest);
    }

    @Override
    public ClassInfo getByMongoId(String mongoId) {
        return classService.getByMongoId(mongoId);
    }

    @Override
    public List<ClassInfo> getAll() {
        return classService.getAll();
    }

    @Override
    public ClassInfo update(ClassRequest classRequest) {
        return classService.update(classRequest);
    }

    @Override
    public void delete(String mongoId) {
        classService.delete(mongoId);
    }
}
