package sg.nus.iss.team9ad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfig {
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        // Allow requests from your React app's local origin (e.g., http://localhost:3000)
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("https://wbgtgroup9.azurewebsites.net");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        // Allow credentials (e.g., cookies)
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
