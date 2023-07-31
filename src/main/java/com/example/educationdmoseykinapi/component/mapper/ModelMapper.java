package com.example.educationdmoseykinapi.component.mapper;

import com.example.educationdmoseykinapi.dto.model.ModelCard;
import com.example.educationdmoseykinapi.dto.model.ModelRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapper {

    @Mapping(source = "classInfo.classMongoId", target = "classMongoId")
    ModelRequest toModelRequest(ModelCard modelResponse);
}
