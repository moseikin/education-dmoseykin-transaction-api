package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import com.example.educationdmoseykinapi.dto.clazz.ClassRequest;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassClientImpl implements ClassClient {
    private static final String CLASS_SERVICE_URI = "http://localhost:8093/class";
    private static final String METHOD_SAVE = "save";
    private static final String METHOD_GET_BY_MONGO_ID = "getByMongoId";
    private static final String METHOD_GET_ALL = "getAll";
    private static final String METHOD_UPDATE = "update";
    private static final String METHOD_DELETE = "delete";
    private static final String METHOD_GET_BY_RELATED_MODEL_MONGO_ID = "getByRelatedModelMongoId";

    @Override
    public ClassInfo save(ClassRequest request) {
        return makeRequest(request, METHOD_SAVE);
    }

    @Override
    public ClassInfo getByMongoId(String mongoId) {
        return makeRequest(mongoId, METHOD_GET_BY_MONGO_ID);
    }

    @Override
    public List<ClassInfo> getAll() {
        return makeRequestForList();
    }

    @Override
    public ClassInfo update(ClassRequest classRequest) {
        return makeRequest(classRequest, METHOD_UPDATE);
    }

    @Override
    public void delete(String mongoId) {
        makeRequest(mongoId, METHOD_DELETE);
    }

    @Override
    public ClassInfo getByRelatedModelMongoId(String modelMongoId) {
        return makeRequest(modelMongoId, METHOD_GET_BY_RELATED_MODEL_MONGO_ID);
    }

    private ClassInfo makeRequest(Object request, String methodName) {
        try {
            JsonRpcHttpClient jsonRpcHttpClient = new JsonRpcHttpClient(new URL(CLASS_SERVICE_URI));
            return jsonRpcHttpClient.invoke(methodName, Collections.singletonList(request), ClassInfo.class);
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable.getMessage());
        }
    }

    private List<ClassInfo> makeRequestForList() {
        try {
            JsonRpcHttpClient jsonRpcHttpClient = new JsonRpcHttpClient(new URL(CLASS_SERVICE_URI));
            return Arrays.asList(jsonRpcHttpClient.invoke(ClassClientImpl.METHOD_GET_ALL, null, ClassInfo[].class));
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable.getMessage());
        }
    }
}
