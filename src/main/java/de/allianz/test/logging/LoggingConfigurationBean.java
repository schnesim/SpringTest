package de.allianz.test.logging;

import org.springframework.stereotype.Component;

@Component
public class LoggingConfigurationBean {
    
    private boolean restLoggingEnabled;

    public boolean isRestLoggingEnabled() {
        return restLoggingEnabled;
    }

    public void setRestLoggingEnabled(boolean restLoggingEnabled) {
        this.restLoggingEnabled = restLoggingEnabled;
    }
    
    
}
