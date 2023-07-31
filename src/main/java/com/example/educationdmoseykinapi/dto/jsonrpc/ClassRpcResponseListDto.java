package com.example.educationdmoseykinapi.dto.jsonrpc;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassRpcResponseListDto {

    private String id;

    private String jsonrpc;

    private List<ClassInfo> result;
}
