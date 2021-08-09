package com.kciamaga.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kciamaga.demo.dto.LocationDto;
import com.kciamaga.demo.dto.weatherBitApiDto.WeatherApiResponseDto;
import com.kciamaga.demo.enums.ErrorCode;
import com.kciamaga.demo.enums.Location;
import com.kciamaga.demo.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherClient {

    private static final String WEATHER_APP_CLIENT_URL = "https://api.weatherbit.io/v2.0/forecast/";
    private final RestTemplate restTemplate = new RestTemplate();

    @Value(value = "${weather.api.user.key}")
    private  String API_KEY;

    private final ObjectMapper objectMapper;


    public List<WeatherApiResponseDto> getWeather() throws ApiException {

       final List<LocationDto> locations = getLocations();
       final List<WeatherApiResponseDto> responseDtos = new ArrayList<>();

        for(LocationDto locationDto : locations) {
            String value = restTemplate.getForObject(WEATHER_APP_CLIENT_URL + "daily?city={city},{country}&key={apiKey}", String.class, locationDto.getCity(), locationDto.getCountryCode(), API_KEY);
            try {
                WeatherApiResponseDto weatherApiResponseDto= objectMapper.readValue(value, WeatherApiResponseDto.class);
                responseDtos.add(weatherApiResponseDto);
            } catch (JsonProcessingException e) {
                log.debug("Error during mapping response to weather dto");
                throw new ApiException(ErrorCode.RESPONSE_MAPPING_ERROR, HttpStatus.NOT_FOUND);
            }
        }
        return responseDtos;
    }


    private List<LocationDto> getLocations() {
        List<LocationDto> locationDtos = new ArrayList<>();
        final Location[] locations = Location.values();

        for(Location location : locations) {
            for(String city : location.getLocations()) {
                LocationDto locationDto = new LocationDto();
                locationDto.setCity(city);
                locationDto.setCountry(location.getCountry());
                locationDto.setCountryCode(location.getCountryCode());
                locationDtos.add(locationDto);
            }
        }
        return locationDtos;
    }
}
