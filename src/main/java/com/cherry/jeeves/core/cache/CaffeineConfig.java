package com.cherry.jeeves.core.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wuweifeng wrote on 2018/4/16.
 */
@Component
@EnableCaching
public class CaffeineConfig {
    
    @Bean
    public CacheManager cacheManager() {
        return new CaffeineCacheManager();
    }

    @Bean
    public Cache cache() {
         return cacheManager().getCache("room_cache");
    }

}
