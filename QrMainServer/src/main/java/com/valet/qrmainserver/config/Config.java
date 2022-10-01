package com.valet.qrmainserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {
        return http.csrf().disable().httpBasic().and().authorizeRequests()
                .antMatchers("/registration", "/do/**", "/getOrganizationsName", "/test").permitAll()
                .antMatchers("/getToken", "/changeJobTitle", "/changeOrganization", "/getRecord").hasAnyAuthority("TEAM_LEAD", "ORGANIZATION_CREATOR", "DIRECTOR")
                .antMatchers("/getToken", "/getMyTeam", "/changeJobTitle", "/getRecord", "/addToTeam").hasAnyAuthority("TEAM_LEAD")
                .antMatchers("/createTeam").hasAnyAuthority("ORGANIZATION_CREATOR", "DIRECTOR")
                .antMatchers("/setRecord").hasAuthority("USER")
                .antMatchers("/deleteOrganization", "/changeOwnerOrganization").hasAnyAuthority("ORGANIZATION_CREATOR" )
                .anyRequest().authenticated()
                .and().build();
    }

    @Bean
    public NewTopic emailTopic() {
        return TopicBuilder.name("sendEmailTopic")
                .partitions(11)
                .replicas(11)
                .build();
    }

    @Bean
    public NewTopic actionTopic(){
        return TopicBuilder.name("doActionTopic")
                .partitions(11)
                .replicas(11)
                .build();
    }

}
