package de.allianz.test.interceptor.configuration;

import de.allianz.test.logging.LogGetRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestResponseInterceptorConfiguration implements WebMvcConfigurer {
    
    @Autowired
    private LogGetRequestInterceptor getInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor);
    }
    
}
