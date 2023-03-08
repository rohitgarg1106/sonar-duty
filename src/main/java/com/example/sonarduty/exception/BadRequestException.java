package com.example.sonarduty.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

public class BadRequestException extends HttpClientErrorException {
    public BadRequestException(HttpStatus statusCode) {
        super(statusCode);
    }

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public BadRequestException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public BadRequestException(HttpStatus statusCode, String statusText, byte[] body, Charset responseCharset) {
        super(statusCode, statusText, body, responseCharset);
    }

    public BadRequestException(HttpStatus statusCode, String statusText, HttpHeaders headers, byte[] body, Charset responseCharset) {
        super(statusCode, statusText, headers, body, responseCharset);
    }

    public BadRequestException(String message, HttpStatus statusCode, String statusText, HttpHeaders headers, byte[] body, Charset responseCharset) {
        super(message, statusCode, statusText, headers, body, responseCharset);
    }
}
