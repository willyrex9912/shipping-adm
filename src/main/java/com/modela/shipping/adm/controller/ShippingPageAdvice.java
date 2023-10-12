package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.ShippingPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collection;

@ControllerAdvice
@Slf4j
public class ShippingPageAdvice implements ResponseBodyAdvice<ShippingPage<Collection<?>, Long>> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().isAssignableFrom(ShippingPage.class);
    }

    @Override
    public ShippingPage<Collection<?>, Long> beforeBodyWrite(ShippingPage<Collection<?>, Long> body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body != null) response.getHeaders().add("X-Total-Count", String.valueOf(body.count));
        return body;
    }
}
