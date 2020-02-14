package de.allianz.test.endpoint;

import de.allianz.test.logging.LoggingConfigurationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToggleLogEndpoint {
    
    @Autowired
    private LoggingConfigurationBean loggingConfiguration;
    
    public ToggleLogEndpoint() {
        System.out.println("ToggleLogEndpoint constructor");
    }
    
    @RequestMapping(name = "/toggleLog", method = {RequestMethod.GET})
    public String toggleSoapLog() {
        loggingConfiguration.setRestLoggingEnabled(!loggingConfiguration.isRestLoggingEnabled());
        
        return "rest log toggled to " + String.valueOf(loggingConfiguration.isRestLoggingEnabled());
    }
}
