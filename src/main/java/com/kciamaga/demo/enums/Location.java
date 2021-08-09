package com.kciamaga.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@Getter
public enum Location {

    POLAND("Poland", "PL", Arrays.asList("Jastarnia", "Warszawa")),
    BRAZIL("Brazil", "BR", Arrays.asList("Fortaleza"));

    private final String country;
    private final String countryCode;
    private final List<String> locations;


    public static String getCountryByCountryCode(String countryCode) {

        for(Location l : Location.values()) {
            if(l.countryCode.equals(countryCode)) {
                return l.country;
            }
        }
        throw new IllegalArgumentException();
    }
}
