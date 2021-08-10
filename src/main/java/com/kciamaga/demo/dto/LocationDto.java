package com.kciamaga.demo.dto;


import com.kciamaga.demo.enums.Countries;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationDto {

    private String city;
    private Countries country;
    private double lat;
    private double lon;

}
