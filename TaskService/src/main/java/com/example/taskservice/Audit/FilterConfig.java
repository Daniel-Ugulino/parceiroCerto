package com.example.taskservice.Audit;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
        @Bean
        public FilterRegistrationBean<JwtFilter> filterRegistrationBean() {
            FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
            registrationBean.setFilter(new JwtFilter());
            registrationBean.addUrlPatterns("/category/*");
            registrationBean.addUrlPatterns("/request/*");
            registrationBean.addUrlPatterns("/task*");
            return registrationBean;
        }
}

