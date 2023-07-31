package com.example.educationdmoseykinapi.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModelRequest {

    private String id;

    private String name;

    private String comment;

    private String classMongoId;
}
