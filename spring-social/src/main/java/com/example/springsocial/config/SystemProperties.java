package com.example.springsocial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SystemProperties {
    @Bean
    public Map<String,String> emailProviders(){
        Map<String,String> map = new HashMap<>();
        map.put("gmail","google");
        map.put("ncsu","google");
        return map;
    }
}
