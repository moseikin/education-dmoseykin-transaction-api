package com.example.educationdmoseykinapi.dto.jsonrpc;

import com.example.educationdmoseykinapi.dto.model.ModelCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelRpcResponseDto {
    private String id;
    private String jsonrpc;
    private ModelCard result;
}
