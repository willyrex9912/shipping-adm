package com.modela.shipping.adm.util.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modela.shipping.adm.util.ShippingConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ShippingException extends Exception {

    private HttpStatus status;
    private ShippingExceptionMessage msg;

    private ShippingException() {
    }

    public ShippingException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.msg = new ShippingExceptionMessage(message, this.status);
    }

    public ShippingException withStatus(HttpStatus status) {
        this.status = status;
        this.msg.status = status;
        return this;
    }

    @Getter
    @Setter
    public static class ShippingExceptionMessage {
        private HttpStatus status;
        private String message;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShippingConstant.DATETIME_FORMAT)
        private LocalDateTime entryDate;

        public ShippingExceptionMessage(String message, HttpStatus status) {
            this.message = message;
            this.status = status;
            this.entryDate = LocalDateTime.now();
        }
    }
}
