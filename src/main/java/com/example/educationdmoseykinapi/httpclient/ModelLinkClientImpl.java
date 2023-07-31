package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.jsonrpc.ModelLinkRpcListResponseDto;
import com.example.educationdmoseykinapi.dto.jsonrpc.ModelLinkRpcResponseDto;
import com.example.educationdmoseykinapi.dto.jsonrpc.RpcRequestDto;
import com.example.educationdmoseykinapi.dto.model.ModelLinkRequest;
import com.example.educationdmoseykinapi.dto.model.ModelLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
    private static final String DEFAULT_MESSAGE_ID = "1";

    private final RestTemplate restTemplate;

    @Override
    public ModelLinkResponse save(ModelLinkRequest modelLinkRequest) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_LINK_METHOD_SAVE, Collections.singletonList(modelLinkRequest));
        return makeRequest(rpcRequestDto);
    }

    @Override
    public ModelLinkResponse getByMongoId(String id) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_LINK_METHOD_GET, Collections.singletonList(id));
        return makeRequest(rpcRequestDto);

    }

    @Override
    public List<ModelLinkResponse> getAll() {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_LINK_METHOD_GET_ALL, null);
        return makeRequestForList(rpcRequestDto);
    }

    @Override
    public ModelLinkResponse update(ModelLinkRequest modelLinkRequest) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_LINK_METHOD_UPDATE, Collections.singletonList(modelLinkRequest));
        return makeRequest(rpcRequestDto);
    }

    @Override
    public void delete(String id) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_LINK_METHOD_DELETE, Collections.singletonList(id));
        makeRequest(rpcRequestDto);
    }

    private RpcRequestDto createRpcRequestDto(String method, List<Object> params) {
        return new RpcRequestDto(DEFAULT_MESSAGE_ID, method, params);
    }

    public ModelLinkResponse makeRequest(RpcRequestDto rpcRequestDto) {
        try {
            ResponseEntity<ModelLinkRpcResponseDto> responseEntity = restTemplate.exchange(new URI(MODEL_LINK_SERVICE_URI),
                    HttpMethod.POST,
                    new HttpEntity<>(rpcRequestDto),
                    ModelLinkRpcResponseDto.class);
            return getResponse(responseEntity.getBody());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private ModelLinkResponse getResponse(ModelLinkRpcResponseDto modelLinkRpcResponseDto) {
        return modelLinkRpcResponseDto != null
                ? modelLinkRpcResponseDto.getResult()
                : new ModelLinkResponse();
    }

    private List<ModelLinkResponse> makeRequestForList(RpcRequestDto rpcRequestDto) {
        try {
            ResponseEntity<ModelLinkRpcListResponseDto> responseEntity = restTemplate.exchange(new URI(MODEL_LINK_SERVICE_URI),
                    HttpMethod.POST,
                    new HttpEntity<>(rpcRequestDto),
                    ModelLinkRpcListResponseDto.class);
            return getModelLinkResponses(responseEntity.getBody());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private List<ModelLinkResponse> getModelLinkResponses(ModelLinkRpcListResponseDto modelLinkRpcListResponseDto) {
        return modelLinkRpcListResponseDto != null
                ? modelLinkRpcListResponseDto.getResult()
                : new ArrayList<>();
    }
}
