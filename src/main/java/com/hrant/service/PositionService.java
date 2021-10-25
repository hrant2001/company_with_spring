package com.hrant.service;

import com.hrant.dto.PositionDto;
import com.hrant.model.Position;
import com.hrant.repository.PositionRepository;
import com.hrant.util.mapper.PositionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PositionService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PositionService.class);

    private final PositionRepository positionRepository;

    private final PositionMapper positionMapper;

    @Autowired
    public PositionService(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    public List<PositionDto> findAllPositions() {
        List<PositionDto> positionsDto = new ArrayList<>();
        List<Position> positions = positionRepository.findAll();

        for (Position p : positions) {
            PositionDto positionDto = positionMapper.toDto(p);
            positionsDto.add(positionDto);
        }

        return positionsDto;
    }
}
