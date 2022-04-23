package br.com.springawsms.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseException> handleInternalServerError
            (Exception exception, WebRequest request){
        ResponseException responseException = ResponseException.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    public final ResponseEntity<ResponseException> handleInternalUnsupportedMathOperation
            (NumberFormatException exception, WebRequest request){
        ResponseException responseException = ResponseException.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseException> handlePersonNotFoundException(EntityNotFoundException exception, WebRequest request){
        ResponseException responseException = ResponseException.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidJWTAuthenticationException.class)
    public ResponseEntity<ResponseException> handleInvalidJWTAuthenticationException(Exception exception, WebRequest request){
        ResponseException responseException = ResponseException.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }
}
