package com.example.educationdmoseykinapi.httpclient;

import com.example.educationdmoseykinapi.dto.jsonrpc.ModelLinkRpcResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RequestService<T, L> {

    private final RestTemplate restTemplate;

//    public <T> ResponseEntity<T> makeRequest() {
//
//    }
//
//    public <L> ResponseEntity<L> makeRequest() {
//
//    }
}
