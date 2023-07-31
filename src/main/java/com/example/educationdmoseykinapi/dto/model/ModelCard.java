package com.example.educationdmoseykinapi.dto.model;

import com.example.educationdmoseykinapi.dto.clazz.ClassInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelCard {

    private String id;

    private String name;

    private String comment;

    private String createAt;

    private String updateAt;

    private String creator;

    private String updater;

    private ClassInfo classInfo;
}
