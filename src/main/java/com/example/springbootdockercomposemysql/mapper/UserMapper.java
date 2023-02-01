package com.example.springbootdockercomposemysql.mapper;

import com.example.springbootdockercomposemysql.dto.UserTo;
import com.example.springbootdockercomposemysql.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserTo> {

//  @Mapping(target = "email", expression = "java(to.getEmail().toLowerCase())")
  @Override
  User toEntity(UserTo to);

  @Mapping(target = "id", ignore = true)
//  @Mapping(target = "email", expression = "java(to.getEmail().toLowerCase())")
  @Override
  User updateFromTo(@MappingTarget User entity, UserTo to);
}
