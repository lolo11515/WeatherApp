package com.kciamaga.demo.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_DATE_EXCEPTION("Provided date must be in 16 days range."),
    RESPONSE_MAPPING_ERROR("Error during mapping response"),
    NO_LOCATION_IS_SUITABLE_FOR_SURFING("No location is suitable for surfing.");

    private String descritpion;


}
