package com.example.educationdmoseykinapi.dto.clazz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ClassInfo {

    private final String classMongoId;

    private final String classTitle;
}
