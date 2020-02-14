package de.allianz.test.interceptor;

import de.allianz.test.Logger;
import java.lang.reflect.Type;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

@ControllerAdvice
public class LogNonGetRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
            MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        Logger l = new Logger();
        l.logRequest(httpServletRequest, body);

        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, java.lang.reflect.Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
}
