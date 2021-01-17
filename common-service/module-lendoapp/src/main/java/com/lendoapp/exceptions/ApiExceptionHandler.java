package com.lendoapp.exceptions;

import com.lendoapp.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.lendoapp")
@RestController
@SuppressWarnings("rawtypes")
public class ApiExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {ApiException.class})
    public ApiResponse handleGenericException(ApiException e, HttpServletRequest httpServletRequest) throws Exception {
        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, null, e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationException.class})
    public ApiResponse handleGenericException(ValidationException e, HttpServletRequest httpServletRequest) throws Exception {
        return new ApiResponse(HttpStatus.BAD_REQUEST, false, null, e.getMessage());
    }


}
