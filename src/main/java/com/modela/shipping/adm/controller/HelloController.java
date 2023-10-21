package com.modela.shipping.adm.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "Hello, world!";
    }

    @PostMapping
    public Credentials sayHelloPost(@AuthenticationPrincipal UserDetails userDetails) {
        return new Credentials(userDetails.getUsername(), userDetails.getPassword());
    }

    public record Credentials(String username, String password) {}
}
