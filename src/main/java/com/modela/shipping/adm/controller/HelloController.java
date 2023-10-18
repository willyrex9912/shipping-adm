package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.security.AuthRequired;
import com.modela.shipping.adm.security.ContextData;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "Hello, world!";
    }

    @AuthRequired
    @GetMapping("/user")
    public String sayHelloUser() {
        ContextData contextData = (ContextData) SecurityContextHolder.getContext().getAuthentication();
        return "Hello user " + (contextData == null ? "NULL" : contextData.getName());
    }

    @PostMapping
    public Credentials sayHelloPost(@RequestBody Credentials credentials) {
        return new Credentials("Hello", "World!!");
    }

    public record Credentials(String username, String password) {}
}
