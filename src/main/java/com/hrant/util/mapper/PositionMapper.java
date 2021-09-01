package com.hrant.util.mapper;

import com.hrant.dto.PositionDto;
import com.hrant.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper extends AbstractMapper<Position, PositionDto> {

    @Autowired
    PositionMapper() {
        super(Position.class, PositionDto.class);
    }
}
