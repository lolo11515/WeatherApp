package com.kciamaga.demo.service;

import com.kciamaga.demo.dto.LocationDto;
import com.kciamaga.demo.dto.weatherBitApiDto.WeatherApiResponseDataDto;
import com.kciamaga.demo.dto.weatherBitApiDto.WeatherApiResponseDto;
import com.kciamaga.demo.enums.ErrorCode;
import com.kciamaga.demo.exception.ApiException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private  WeatherService weatherService;


    @Test
    void shouldThrowExceptionOfInvalidDate() throws ApiException {
        //given
        LocalDate date = LocalDate.now().plusDays(17);
        
        //when
        ApiException exception =  assertThrows(ApiException.class, () -> this.weatherService.getBestLocationForSurfing(date));

        //then
        assertEquals(ErrorCode.INVALID_DATE_EXCEPTION, exception.getErrorCode());
    }


    @Test
    void getBestLocationForSurfingTest() throws ApiException {
        //given
        Mockito.when(this.weatherClient.getWeather(any(Long.class))).thenReturn(prepareWeatherApiResponseDtos());

        //when
        String result = this.weatherService.getBestLocationForSurfing(LocalDate.of(2021,8,11));

        //then
        assertEquals("Jastarnia Poland", result);
    }



    private List<WeatherApiResponseDto> prepareWeatherApiResponseDtos() {

        List<WeatherApiResponseDto> apiResponseDtos = new ArrayList<>();

        WeatherApiResponseDto responseDtoJastarnia = new WeatherApiResponseDto();
        responseDtoJastarnia.setCity_name("Jastarnia");
        responseDtoJastarnia.setCountry_code("PL");

        WeatherApiResponseDataDto[]  dataDtosJastarnia = new WeatherApiResponseDataDto[2];

        LocalDate date = LocalDate.of(2021,8,10);

        WeatherApiResponseDataDto dataDto = new WeatherApiResponseDataDto();
        dataDto.setDatetime(date);
        dataDto.setTemp(23.7);
        dataDto.setWind_spd(17.7);
        dataDtosJastarnia[0] = dataDto;

        WeatherApiResponseDataDto dataDto2 = new WeatherApiResponseDataDto();
        dataDto2.setDatetime(date.plusDays(1));
        dataDto2.setTemp(21.7);
        dataDto2.setWind_spd(7);
        dataDtosJastarnia[1] = dataDto2;

        responseDtoJastarnia.setData(dataDtosJastarnia);

        apiResponseDtos.add(responseDtoJastarnia);

        WeatherApiResponseDto responseDtoWarszawa = new WeatherApiResponseDto();
        responseDtoWarszawa.setCity_name("Warszawa");
        responseDtoWarszawa.setCountry_code("PL");

        WeatherApiResponseDataDto[]  dataDtosWarszawa = new WeatherApiResponseDataDto[2];

        WeatherApiResponseDataDto dataDto3 = new WeatherApiResponseDataDto();
        dataDto3.setDatetime(date);
        dataDto3.setTemp(16.7);
        dataDto3.setWind_spd(16.9);
        dataDtosWarszawa[0] = dataDto3;

        WeatherApiResponseDataDto dataDto4 = new WeatherApiResponseDataDto();
        dataDto4.setDatetime(date.plusDays(1));
        dataDto4.setTemp(15.7);
        dataDto4.setWind_spd(8.9);
        dataDtosWarszawa[1] = dataDto4;

        responseDtoWarszawa.setData(dataDtosWarszawa);

        apiResponseDtos.add(responseDtoWarszawa);

        return apiResponseDtos;

    }

}