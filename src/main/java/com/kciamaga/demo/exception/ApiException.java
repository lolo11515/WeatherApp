package com.kciamaga.demo.exception;

import com.kciamaga.demo.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiException extends Exception {

    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

}
