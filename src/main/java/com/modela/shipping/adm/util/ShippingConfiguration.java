package com.modela.shipping.adm.util;

import com.modela.shipping.adm.security.TokenFilter;
import com.modela.shipping.adm.service.AdmTokenCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ShippingConfiguration {

    private final ApplicationContext applicationContext;
    private final AdmTokenCredentialService tokenCredentialService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/**");
    }

    @Bean
    public FilterRegistrationBean<TokenFilter> credentialFilter(){
        FilterRegistrationBean<TokenFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new TokenFilter(this.applicationContext, this.tokenCredentialService));
        bean.addUrlPatterns("/*");
        return bean;
    }
}
