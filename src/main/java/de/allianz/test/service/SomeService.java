package de.allianz.test.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SomeService {
    
    private String valueToBeCached = "someValue";
    private boolean gotCached = false;
    
    @Cacheable("addresses")
    public String getSomething(String param) {
        System.out.println("Value not found in cache");
        return valueToBeCached;
    }
    
    @CacheEvict(value = "addresses", allEntries = true)
    @Scheduled(cron = "0 7,8 15 * * *")
//    @Scheduled(cron = "0/5 * * * * *")
    public void cacheCleanup() {
        System.out.println("Cleaning up");
    }
}
