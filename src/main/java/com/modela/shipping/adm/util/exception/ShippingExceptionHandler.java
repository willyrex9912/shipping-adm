package com.modela.shipping.adm.util.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ShippingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ShippingException.class)
    public ResponseEntity<?> handleShippingException(ShippingException ex) {
        return new ResponseEntity<>(ex.getMsg(), ex.getStatus());
    }
}
