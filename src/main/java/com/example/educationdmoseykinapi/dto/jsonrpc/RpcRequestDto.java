package com.example.educationdmoseykinapi.dto.jsonrpc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class RpcRequestDto {

    private final String id;
    private final String method;
    private final List<Object> params;
}
