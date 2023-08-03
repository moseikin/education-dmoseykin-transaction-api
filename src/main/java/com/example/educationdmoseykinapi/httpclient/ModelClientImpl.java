package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.model.ModelCard;
import com.example.educationdmoseykinapi.dto.model.ModelRequest;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelClientImpl implements ModelClient {
    private static final String MODEL_SERVICE_URI = "http://localhost:8099/model";
    private static final String MODEL_METHOD_SAVE = "save";
    private static final String MODEL_METHOD_GET = "get";
    private static final String MODEL_METHOD_GET_ALL = "getAll";
    private static final String MODEL_METHOD_UPDATE = "update";
    private static final String MODEL_METHOD_DELETE = "delete";

    @Override
    public ModelCard save(ModelRequest modelRequest) {
        return makeRequest(modelRequest, MODEL_METHOD_SAVE);
    }

    @Override
    public ModelCard get(String id) {
        return makeRequest(id, MODEL_METHOD_GET);
    }

    @Override
    public List<ModelCard> getAll() {
        return makeRequestForList();
    }

    @Override
    public ModelCard update(ModelRequest modelRequest) {
        return makeRequest(modelRequest, MODEL_METHOD_UPDATE);
    }

    @Override
    public void delete(String id) {
        makeRequest(id, MODEL_METHOD_DELETE);
    }

    private ModelCard makeRequest(Object request, String methodName) {
        try {
            JsonRpcHttpClient jsonRpcHttpClient = new JsonRpcHttpClient(new URL(MODEL_SERVICE_URI));
            return jsonRpcHttpClient.invoke(methodName, Collections.singletonList(request), ModelCard.class);
        } catch (Throwable e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private List<ModelCard> makeRequestForList() {
        try {
            JsonRpcHttpClient jsonRpcHttpClient = new JsonRpcHttpClient(new URL(MODEL_SERVICE_URI));
            return Arrays.asList(jsonRpcHttpClient.invoke(MODEL_METHOD_GET_ALL, null, ModelCard[].class));
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable.getMessage());
        }
    }
}
