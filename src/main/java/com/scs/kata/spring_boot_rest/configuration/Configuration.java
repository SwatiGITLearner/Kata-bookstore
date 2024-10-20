package com.scs.kata.spring_boot_rest.configuration;

import org.modelmapper.ModelMapper;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;

    }

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder().group("springshop-public").pathsToMatch("/v1/**").build();
    }
}