package com.kciamaga.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeatherDto {

    private String city;
    private String countryCode;
    private double averageTemperature;
    private double windSpeed;

}
