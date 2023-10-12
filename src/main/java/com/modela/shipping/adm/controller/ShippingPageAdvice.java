package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.ShippingPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collection;

@ControllerAdvice
@Slf4j
public class ShippingPageAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ShippingPage<?, ?>) {
            return responseWithCollections((ShippingPage<?, ?>) body, response);
        }

        if (body instanceof ResponseEntity<?> && ((ResponseEntity<?>) body).getBody() instanceof ShippingPage<?, ?>) {
            return responseWithCollections((ShippingPage<?, ?>) ((ResponseEntity<?>) body).getBody(), response);
        }

        return body;
    }

    private Collection<?> responseWithCollections(ShippingPage<?, ?> page, ServerHttpResponse response) {
        log.info("Including header => X-Total-Count: {}", page.count);
        response.getHeaders().add("X-Total-Count", String.valueOf(page.count));
        return page.items;
    }
}
