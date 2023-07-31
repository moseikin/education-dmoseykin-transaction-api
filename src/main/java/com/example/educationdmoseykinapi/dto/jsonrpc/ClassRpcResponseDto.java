package com.example.educationdmoseykinapi.dto.jsonrpc;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassRpcResponseDto {

    private String id;

    private String jsonrpc;

    private ClassInfo result;
}
