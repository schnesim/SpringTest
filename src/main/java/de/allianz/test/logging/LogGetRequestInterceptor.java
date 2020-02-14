package de.allianz.test.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LogGetRequestInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggingConfigurationBean logConfig;

    private Logger l = new Logger();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) {

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.GET.name())) {
            if (logConfig.isRestLoggingEnabled()) {
                l.logRequest(request, null);
            }
        }

        return true;
    }
}
