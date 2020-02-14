package de.allianz.test.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class LogResponseBodyResponseAdapter implements ResponseBodyAdvice<Object> {
    
    @Autowired
    private LoggingConfigurationBean logConfig;

    private Logger l = new Logger();

    @Override
    public boolean supports(MethodParameter methodParameter,
            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            org.springframework.http.server.ServerHttpResponse response) {
        if (request instanceof ServletServerHttpRequest
                && response instanceof ServletServerHttpResponse) {
            Logger l = new Logger();
            if (logConfig.isRestLoggingEnabled()) {
                l.logResponse(
                        ((ServletServerHttpRequest) request).getServletRequest(),
                        ((ServletServerHttpResponse) response).getServletResponse(), body);
            }
        }

        return body;
    }
}
