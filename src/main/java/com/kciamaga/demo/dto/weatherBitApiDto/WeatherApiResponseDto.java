package com.kciamaga.demo.dto.weatherBitApiDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherApiResponseDto {

    private WeatherApiResponseDataDto[] data;
    private String city_name;
    private String country_code;

}
