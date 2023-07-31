package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.jsonrpc.ModelRpcResponseDto;
import com.example.educationdmoseykinapi.dto.jsonrpc.ModelRpcResponseListDto;
import com.example.educationdmoseykinapi.dto.jsonrpc.RpcRequestDto;
import com.example.educationdmoseykinapi.dto.model.ModelCard;
import com.example.educationdmoseykinapi.dto.model.ModelRequest;
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
public class ModelClientImpl implements ModelClient {
    private static final String MODEL_SERVICE_URI = "http://localhost:8099/model";
    private static final String MODEL_METHOD_SAVE = "save";
    private static final String MODEL_METHOD_GET = "get";
    private static final String MODEL_METHOD_GET_ALL = "getAll";
    private static final String MODEL_METHOD_UPDATE = "update";
    private static final String MODEL_METHOD_DELETE = "delete";
    private static final String DEFAULT_MESSAGE_ID = "1";

    private final RestTemplate restTemplate;

    @Override
    public ModelCard save(ModelRequest modelRequest) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_METHOD_SAVE, Collections.singletonList(modelRequest));
        ResponseEntity<ModelRpcResponseDto> responseEntity = makeRequest(rpcRequestDto);
        return getResponse(responseEntity.getBody());
    }

    @Override
    public ModelCard get(String id) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_METHOD_GET, Collections.singletonList(id));
        ResponseEntity<ModelRpcResponseDto> responseEntity = makeRequest(rpcRequestDto);
        return getResponse(responseEntity.getBody());

    }

    @Override
    public List<ModelCard> getAll() {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_METHOD_GET_ALL, null);
        return makeRequestForList(rpcRequestDto);
    }

    @Override
    public ModelCard update(ModelRequest modelRequest) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_METHOD_UPDATE, Collections.singletonList(modelRequest));
        ResponseEntity<ModelRpcResponseDto> responseEntity = makeRequest(rpcRequestDto);
        return getResponse(responseEntity.getBody());
    }

    @Override
    public void delete(String id) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(MODEL_METHOD_DELETE, Collections.singletonList(id));
        makeRequest(rpcRequestDto);
    }

    private RpcRequestDto createRpcRequestDto(String method, List<Object> params) {
        return new RpcRequestDto(DEFAULT_MESSAGE_ID, method, params);
    }

    public ResponseEntity<ModelRpcResponseDto> makeRequest(RpcRequestDto rpcRequestDto) {
        try {
            return restTemplate.exchange(new URI(MODEL_SERVICE_URI),
                    HttpMethod.POST,
                    new HttpEntity<>(rpcRequestDto),
                    ModelRpcResponseDto.class);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private ModelCard getResponse(ModelRpcResponseDto rpcResponseDto) {
        return rpcResponseDto != null
                ? rpcResponseDto.getResult()
                : new ModelCard();
    }

    private List<ModelCard> makeRequestForList(RpcRequestDto rpcRequestDto) {
        try {
            ResponseEntity<ModelRpcResponseListDto> responseEntity = restTemplate.exchange(new URI(MODEL_SERVICE_URI),
                    HttpMethod.POST,
                    new HttpEntity<>(rpcRequestDto),
                    ModelRpcResponseListDto.class);
            return getModelResponses(responseEntity.getBody());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private List<ModelCard> getModelResponses(ModelRpcResponseListDto rpcResponseListDto) {
        return rpcResponseListDto != null
                ? rpcResponseListDto.getResult()
                : new ArrayList<>();
    }
}
