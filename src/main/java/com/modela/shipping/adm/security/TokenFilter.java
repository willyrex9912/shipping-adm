package com.modela.shipping.adm.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Value("${adm.token-public-key}")
    private String publicKeyName = "shippingPublicKey.pem";

    private final ApplicationContext applicationContext;

    public TokenFilter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HandlerMapping handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        try {
            HandlerExecutionChain executionChain = handlerMapping.getHandler(request);
            HandlerMethod handlerMethod = (HandlerMethod) executionChain.getHandler();
            Method method = handlerMethod.getMethod();
            Class<?> beanType = handlerMethod.getBeanType();
            if(
                    !method.isAnnotationPresent(AuthRequired.class) &&
                            !beanType.isAnnotationPresent(AuthRequired.class)
            ){
                filterChain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")){
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = header.split(" ")[1].trim();
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(KeyLoader.loadPublicKey(publicKeyName))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            SecurityContextHolder.getContext().setAuthentication(
                new ContextData(
                        claims.getSubject(),
                        null,
                        claims.get("user", Long.class),
                        claims.get("org", Long.class),
                        claims.get("roles", List.class)
                )
            );
        }catch (Exception e){
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
