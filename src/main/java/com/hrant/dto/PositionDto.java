package com.hrant.dto;

import java.io.Serializable;
import java.util.Objects;

public class PositionDto implements Serializable {
    private Integer positionId;
    private String name;
    private String shortName;

    public PositionDto() {
        name = "";
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionDto positionDto = (PositionDto) o;
        return positionId == positionDto.positionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId);
    }

    @Override
    public String toString() {
        return positionId + " " + name + " " + shortName;
    }
}
