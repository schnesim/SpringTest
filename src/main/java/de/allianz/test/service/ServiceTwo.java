package de.allianz.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceTwo {
    private final ServiceOne serviceOne;
    
    public String callServiceOne() {
        return serviceOne.gimmiText();
    }
}
