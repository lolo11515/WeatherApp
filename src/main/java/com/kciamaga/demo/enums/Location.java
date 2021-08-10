package com.kciamaga.demo.enums;

import com.kciamaga.demo.dto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@Getter
public enum Location {

    POLAND(Arrays.asList(new LocationDto("Warszawa", Countries.POLAND, 67.78, 89.56),
           new LocationDto("Jastarnia",  Countries.POLAND,78.99, 45.34))),
    BRAZIL(Arrays.asList(new LocationDto("Fortaleza", Countries.BRAZIL,45.67, 32.67)));

    private final List<LocationDto> locationDto;

}
