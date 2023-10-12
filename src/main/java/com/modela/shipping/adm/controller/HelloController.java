package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.security.AuthenticationRequired;
import com.modela.shipping.adm.security.ContextData;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "Hello, world!";
    }

    @AuthenticationRequired
    @GetMapping("/user")
    public String sayHelloUser() {
        ContextData contextData = (ContextData) SecurityContextHolder.getContext().getAuthentication();
        return "Hello user " + (contextData == null ? "NULL" : contextData.getUserId());
    }
}
