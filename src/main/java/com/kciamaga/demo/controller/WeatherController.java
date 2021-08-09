package com.kciamaga.demo.controller;

import com.kciamaga.demo.exception.ApiException;
import com.kciamaga.demo.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;


@Slf4j
@RestController()
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam(name = "date")  @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) throws ApiException {
        log.debug("Get weather.");

        String value = weatherService.getBestLocationForSurfing(date);

        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
