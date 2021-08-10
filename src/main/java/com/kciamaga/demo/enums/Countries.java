package com.kciamaga.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Countries {
    POLAND("Poland", "PL"),
    BRAZIL("Brazil", "BR");

    private final String countryName;
    private final String countryCode;

    public static String getCountryByCountryCode(String countryCode) {

        for(Countries l : Countries.values()) {
            if(l.countryCode.equals(countryCode)) {
                return l.countryName;
            }
        }
        throw new IllegalArgumentException();
    }
}
