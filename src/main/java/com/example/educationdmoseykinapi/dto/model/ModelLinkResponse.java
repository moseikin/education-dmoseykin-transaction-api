package com.example.educationdmoseykinapi.dto.model;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelLinkResponse {

    private Long id;

    private String mongoId;

    private String title;

    private ClassInfo classResponse;
}
