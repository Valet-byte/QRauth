package com.valet.qremailserver.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.BytesJsonMessageConverter;

@Configuration
public class Config {

    @Bean
    public BytesJsonMessageConverter bytesJsonMessageConverter(){
        return new BytesJsonMessageConverter();
    }

    @Bean
    public NewTopic emailTopic() {
        return TopicBuilder.name("sendEmailTopic")
                .partitions(11)
                .replicas(11)
                .build();
    }


}
