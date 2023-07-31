package com.example.educationdmoseykinapi.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModelLinkRequest {

    private String mongoId;

    private String title;

    /**
     * MongoId ноды Class, с которой свяжется нода Model
     */
    private String classMongoId;
}
