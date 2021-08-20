package com.hrant.controller;

import com.hrant.dto.PositionDto;
import com.hrant.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        List<PositionDto> positionsDto = positionService.findAllPositions();
        return new ResponseEntity<>(positionsDto, HttpStatus.OK);
    }
}
