package com.kciamaga.demo.service;


import com.kciamaga.demo.comparator.LocationComparator;
import com.kciamaga.demo.dto.WeatherDto;
import com.kciamaga.demo.dto.weatherBitApiDto.WeatherApiResponseDataDto;
import com.kciamaga.demo.dto.weatherBitApiDto.WeatherApiResponseDto;
import com.kciamaga.demo.enums.ErrorCode;
import com.kciamaga.demo.enums.Location;
import com.kciamaga.demo.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    public String getBestLocationForSurfing(LocalDate date) throws ApiException {
        //check if the date is in 16 days range
        validDate(date);

        List<WeatherApiResponseDto> weatherApiResponseDtos = this.weatherClient.getWeather();

        List<WeatherDto> weatherDtos = mapWeatherApiResponseDtoToWeatherDtoAndValid(weatherApiResponseDtos, date);

        if(weatherDtos.size() < 1)
            throw new ApiException(ErrorCode.NO_LOCATION_IS_SUITABLE_FOR_SURFING, HttpStatus.NOT_FOUND);

        WeatherDto result = compareLocations(weatherDtos);

        return result.getCity() + " " + Location.getCountryByCountryCode(result.getCountryCode());
    }


    //todo find better solution
    private WeatherDto compareLocations(List<WeatherDto> weatherDtos) throws ApiException {

        Optional<WeatherDto> response = weatherDtos
                .stream()
                .max(new LocationComparator());

        if(response.isPresent())
            return response.get();
        else {
            throw new ApiException(ErrorCode.RESPONSE_MAPPING_ERROR, HttpStatus.NOT_FOUND);
        }
    }


    private void validDate(LocalDate date) throws ApiException {
        if (!(date.isEqual(LocalDate.now()) || (date.isAfter(LocalDate.now()) && (date.isBefore(LocalDate.now().plusDays(15))) || (date.isEqual(LocalDate.now().plusDays(15)))))) {
            log.debug("Provided date must be in 16 days range");
            throw new ApiException(ErrorCode.INVALID_DATE_EXCEPTION, HttpStatus.BAD_REQUEST);
        }
    }


    private List<WeatherDto> mapWeatherApiResponseDtoToWeatherDtoAndValid(List<WeatherApiResponseDto> responseDtos, LocalDate date) {

        final List<WeatherDto> weatherDtos = new ArrayList<>();

        for (WeatherApiResponseDto response : responseDtos) {
            for (WeatherApiResponseDataDto data : response.getData()) {

                boolean isDateValid = data.getDatetime().equals(date);
                //check if weather is suitable for surfing
                boolean isWeatherSuitableForSurfing = (data.getWind_spd() >= 5 && data.getWind_spd() <= 18) && (data.getTemp() >= 5 && data.getTemp() <= 35);

                if (isDateValid) {
                    if (isWeatherSuitableForSurfing) {
                        WeatherDto weatherDto = new WeatherDto();
                        weatherDto.setCountryCode(response.getCountry_code());
                        weatherDto.setCity(response.getCity_name());
                        weatherDto.setWindSpeed(data.getWind_spd());
                        weatherDto.setAverageTemperature(data.getTemp());
                        weatherDtos.add(weatherDto);
                        log.info("{} is suitable for surfing on {}", response.getCity_name(), data.getDatetime());
                        break;
                    }
                    log.info("{} is not suitable for surfing on {}.", response.getCity_name(), data.getDatetime());
                    break;
                }
            }
        }
        return weatherDtos;
    }
}


