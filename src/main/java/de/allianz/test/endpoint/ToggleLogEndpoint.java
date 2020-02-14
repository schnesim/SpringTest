package de.allianz.test.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToggleLogEndpoint {
    
    private boolean restLoggingEnabled;
    
    public ToggleLogEndpoint() {
        System.out.println("ToggleLogEndpoint constructor");
    }
    
    @RequestMapping(name = "/toggleLog", method = {RequestMethod.GET})
    public String toggleSoapLog() {
        restLoggingEnabled = !restLoggingEnabled;
        
        return String.valueOf(restLoggingEnabled);
    }
}
