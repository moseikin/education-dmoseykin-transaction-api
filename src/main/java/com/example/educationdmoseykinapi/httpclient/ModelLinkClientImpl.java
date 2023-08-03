package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.model.ModelLinkRequest;
import com.example.educationdmoseykinapi.dto.model.ModelLinkResponse;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelLinkClientImpl implements ModelLinkClient {
    private static final String MODEL_LINK_SERVICE_URI = "http://localhost:8093/model";
    private static final String MODEL_LINK_METHOD_SAVE = "save";
    private static final String MODEL_LINK_METHOD_GET = "getByMongoId";
    private static final String MODEL_LINK_METHOD_GET_ALL = "getAll";
    private static final String MODEL_LINK_METHOD_UPDATE = "update";
    private static final String MODEL_LINK_METHOD_DELETE = "delete";

    @Override
    public ModelLinkResponse save(ModelLinkRequest modelLinkRequest) {
        return makeRequest(modelLinkRequest, MODEL_LINK_METHOD_SAVE);
    }

    @Override
    public ModelLinkResponse getByMongoId(String id) {
        return makeRequest(id, MODEL_LINK_METHOD_GET);

    }

    @Override
    public List<ModelLinkResponse> getAll() {
        return makeRequestForList();
    }

    @Override
    public ModelLinkResponse update(ModelLinkRequest modelLinkRequest) {
        return makeRequest(modelLinkRequest, MODEL_LINK_METHOD_UPDATE);
    }

    @Override
    public void delete(String id) {
        makeRequest(id, MODEL_LINK_METHOD_DELETE);
    }

    private ModelLinkResponse makeRequest(Object request, String methodName) {
        try {
            JsonRpcHttpClient jsonRpcHttpClient = new JsonRpcHttpClient(new URL(MODEL_LINK_SERVICE_URI));
            return jsonRpcHttpClient.invoke(methodName, Collections.singletonList(request), ModelLinkResponse.class);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private List<ModelLinkResponse> makeRequestForList() {
        try {
            JsonRpcHttpClient jsonRpcHttpClient = new JsonRpcHttpClient(new URL(MODEL_LINK_SERVICE_URI));
            return Arrays.asList(jsonRpcHttpClient.invoke(MODEL_LINK_METHOD_GET_ALL, null, ModelLinkResponse[].class));
        } catch (Throwable throwable) {

            throw new IllegalStateException(throwable.getMessage());
        }
    }
}
