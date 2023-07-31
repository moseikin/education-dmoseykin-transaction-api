package com.example.educationdmoseykinapi.dto.jsonrpc;

import com.example.educationdmoseykinapi.dto.model.ModelLinkResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelLinkRpcListResponseDto {
    private String id;
    private String jsonrpc;
    private List<ModelLinkResponse> result;
}
