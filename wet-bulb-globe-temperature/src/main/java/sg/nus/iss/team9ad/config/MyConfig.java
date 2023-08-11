package sg.nus.iss.team9ad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;


@Configuration
public class MyConfig {

    @Bean
     RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
