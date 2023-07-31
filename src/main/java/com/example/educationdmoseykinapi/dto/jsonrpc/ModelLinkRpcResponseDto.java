package com.example.educationdmoseykinapi.dto.jsonrpc;

import com.example.educationdmoseykinapi.dto.model.ModelLinkResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelLinkRpcResponseDto {
    private String id;
    private String jsonrpc;
    private ModelLinkResponse result;
}
