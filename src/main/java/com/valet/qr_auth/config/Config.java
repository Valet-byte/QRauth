package com.valet.qr_auth.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class Config {

    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
        return new CacheLoader<>() {
            @Override
            public Object load(Object o){
                System.out.println("data -> " + o);
                return o;
            }
        };
    }

    @Bean("MyCacheManager")
    public CacheManager cacheManager() {

        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(name, CacheBuilder.newBuilder()
                                .expireAfterWrite(2, TimeUnit.MINUTES)
                                .refreshAfterWrite(2, TimeUnit.MINUTES)
                                .build(cacheLoader()).asMap(),
                        false);
            }
        };
    }

}
