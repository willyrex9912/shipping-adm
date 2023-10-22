package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.model.AdmUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "Hello, world!";
    }

    @PostMapping
    public Credentials sayHelloPost(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("orgId: {}", ((AdmUserDetails) userDetails).getOrgId());
        log.info("subOrgId: {}", ((AdmUserDetails) userDetails).getSubOrgId());
        return new Credentials(userDetails.getUsername(), userDetails.getPassword());
    }

    public record Credentials(String username, String password) {}
}
