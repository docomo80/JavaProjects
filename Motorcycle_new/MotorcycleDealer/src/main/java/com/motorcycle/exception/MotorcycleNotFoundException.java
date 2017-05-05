package com.motorcycle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such motorcycle!")
public class MotorcycleNotFoundException extends RuntimeException {
}
