package com.hrant.service;

import com.hrant.dto.PositionDto;
import com.hrant.model.Position;
import com.hrant.repository.PositionRepository;
import com.hrant.util.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PositionService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PositionService.class);

    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public PositionDto findPositionById(int id) {
        return DtoConverter.positionToDto((positionRepository.findById(id).orElse(DtoConverter.dtoToPosition(new PositionDto()))));
    }

    public List<PositionDto> findAllPositions() {
        List<PositionDto> positionsDto = new ArrayList<>();
        List<Position> positions = positionRepository.findAll();

        for (Position p : positions) {
            PositionDto positionDto = DtoConverter.positionToDto(p);
            positionsDto.add(positionDto);
        }

        return positionsDto;
    }

    public int findPositionIdByName(String name) {
        PositionDto positionDto = findAllPositions().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findAny().orElse(null);
        return Objects.nonNull(positionDto) ? positionDto.getPositionId() : -1;
    }
}
