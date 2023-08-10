package sg.nus.iss.team9ad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/protected/**") // Specify the path patterns to be protected
                .excludePathPatterns("/login", "/homepage"); // Exclude login and homepage from protection

        registry.addInterceptor(new AdminAuthorizationInterceptor())
                .addPathPatterns("/api/staffs/**"); // Apply the interceptor to staff operations

    }
}