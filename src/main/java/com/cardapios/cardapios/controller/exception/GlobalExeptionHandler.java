package com.cardapios.cardapios.controller.exception;


import com.cardapios.cardapios.domain.message.ResponseMessage;
import com.cardapios.cardapios.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseMessage> objectNotFound(
            ObjectNotFoundException e, HttpServletRequest request
    ){
        ResponseMessage message = new ResponseMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
