package com.hrant.util.mapper;

import com.hrant.dto.DtoMarker;
import com.hrant.model.EntityMarker;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractMapper<E extends EntityMarker, D extends DtoMarker> implements Mapper<E, D> {

    @Autowired
    protected ModelMapper mapper;

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }

//    Converter<E, D> toDtoConverter() {
//        return context -> {
//            E source = context.getSource();
//            D destination = context.getDestination();
//            mapSpecificFields(source, destination);
//            return context.getDestination();
//        };
//    }
//
//
//    Converter<D, E> toEntityConverter() {
//        return context -> {
//            D source = context.getSource();
//            E destination = context.getDestination();
//            mapSpecificFields(source, destination);
//            return context.getDestination();
//        };
//    }
//
//    abstract void mapSpecificFields(E source, D destination);
//
//    abstract void mapSpecificFields(D source, E destination);
}
