package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import com.example.educationdmoseykinapi.dto.clazz.ClassRequest;
import com.example.educationdmoseykinapi.dto.jsonrpc.ClassRpcResponseDto;
import com.example.educationdmoseykinapi.dto.jsonrpc.ClassRpcResponseListDto;
import com.example.educationdmoseykinapi.dto.jsonrpc.RpcRequestDto;
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
public class ClassClientImpl implements ClassClient {
    private static final String CLASS_SERVICE_URI = "http://localhost:8093/class";
    private static final String DEFAULT_MESSAGE_ID = "1";
    private static final String METHOD_SAVE = "save";
    private static final String METHOD_GET_BY_MONGO_ID = "getByMongoId";
    private static final String METHOD_GET_ALL = "getAll";
    private static final String METHOD_UPDATE = "update";
    private static final String METHOD_DELETE = "delete";
    private static final String METHOD_GET_BY_RELATED_MODEL_MONGO_ID = "getByRelatedModelMongoId";

    private final RestTemplate restTemplate;

    @Override
    public ClassInfo save(ClassRequest request) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(METHOD_SAVE, Collections.singletonList(request));
        return makeRequest(rpcRequestDto);
    }

    @Override
    public ClassInfo getByMongoId(String mongoId) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(METHOD_GET_BY_MONGO_ID, Collections.singletonList(mongoId));
        return makeRequest(rpcRequestDto);
    }

    @Override
    public List<ClassInfo> getAll() {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(METHOD_GET_ALL, null);
        return makeRequestForList(rpcRequestDto);
    }

    @Override
    public ClassInfo update(ClassRequest classRequest) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(METHOD_UPDATE, Collections.singletonList(classRequest));
        return makeRequest(rpcRequestDto);
    }

    @Override
    public void delete(String mongoId) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(METHOD_DELETE, Collections.singletonList(mongoId));
        makeRequest(rpcRequestDto);
    }

    @Override
    public ClassInfo getByRelatedModelMongoId(String modelMongoId) {
        RpcRequestDto rpcRequestDto = createRpcRequestDto(METHOD_GET_BY_RELATED_MODEL_MONGO_ID,
                Collections.singletonList(modelMongoId));
        return makeRequestWithClassInfo(rpcRequestDto);
    }

    private ClassInfo makeRequestWithClassInfo(RpcRequestDto rpcRequestDto) {
        try {
            ResponseEntity<ClassRpcResponseDto> responseEntity = restTemplate.exchange(new URI(CLASS_SERVICE_URI),
                    HttpMethod.POST,
                    new HttpEntity<>(rpcRequestDto),
                    ClassRpcResponseDto.class);
            return getClassInfo(responseEntity.getBody());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private ClassInfo getClassInfo(ClassRpcResponseDto classRpcInfoResponseDto) {
        if (classRpcInfoResponseDto != null) {
            return classRpcInfoResponseDto.getResult();
        } else {
            return new ClassInfo();
        }
    }

    private RpcRequestDto createRpcRequestDto(String method, List<Object> params) {
        return new RpcRequestDto(DEFAULT_MESSAGE_ID, method, params);
    }

    public ClassInfo makeRequest(RpcRequestDto rpcRequestDto) {
        try {
            ResponseEntity<ClassRpcResponseDto> responseEntity = restTemplate.exchange(new URI(CLASS_SERVICE_URI),
                    HttpMethod.POST,
                    new HttpEntity<>(rpcRequestDto),
                    ClassRpcResponseDto.class);
            return getClassResponse(responseEntity.getBody());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private ClassInfo getClassResponse(ClassRpcResponseDto classRpcResponseDto) {
        if (classRpcResponseDto != null) {
            return classRpcResponseDto.getResult();
        } else {
            return new ClassInfo();
        }
    }

    private List<ClassInfo> makeRequestForList(RpcRequestDto rpcRequestDto) {
        try {
            ResponseEntity<ClassRpcResponseListDto> responseEntity = restTemplate.exchange(new URI(CLASS_SERVICE_URI),
                    HttpMethod.POST,
                    new HttpEntity<>(rpcRequestDto),
                    ClassRpcResponseListDto.class);
            return getClassResponses(responseEntity.getBody());

        } catch (RuntimeException | URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private List<ClassInfo> getClassResponses(ClassRpcResponseListDto classRpcResponseListDto) {
        return classRpcResponseListDto != null
                ? classRpcResponseListDto.getResult()
                : new ArrayList<>();
    }
}
