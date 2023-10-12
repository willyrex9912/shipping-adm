package com.modela.shipping.adm.util;

import com.modela.shipping.adm.security.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ShippingConfiguration {

    @Autowired
    ApplicationContext applicationContext;

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
        bean.setFilter(new TokenFilter(this.applicationContext));
        bean.addUrlPatterns("/*");
        return bean;
    }
}
