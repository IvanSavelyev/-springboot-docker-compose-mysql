package com.example.springbootdockercomposemysql.mapper;

import java.util.Collection;
import java.util.List;
import org.mapstruct.MappingTarget;

public interface BaseMapper<E, T> {

  E toEntity(T to);

  List<E> toEntityList(Collection<T> tos);

  E updateFromTo(@MappingTarget E entity, T to);

  T toTo(E entity);

  List<T> toToList(Collection<E> entities);
}
