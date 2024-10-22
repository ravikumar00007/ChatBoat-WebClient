package com.chatboatwebclient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        /*RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(((request, body, execution) ->{
            request.getHeaders().add("x-goog-api-key","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

            return execution.execute(request, body);
        } ));
        return restTemplate;*/
        return new RestTemplate();
    }

}
