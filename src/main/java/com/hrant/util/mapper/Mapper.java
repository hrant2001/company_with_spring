package com.hrant.util.mapper;

import com.hrant.dto.DtoMarker;
import com.hrant.model.EntityMarker;

public interface Mapper<E extends EntityMarker, D extends DtoMarker> {

    E toEntity(D dto);

    D toDto(E entity);
}
