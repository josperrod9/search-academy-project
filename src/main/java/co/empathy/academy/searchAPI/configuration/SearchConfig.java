package co.empathy.academy.searchAPI.configuration;

import co.empathy.academy.searchAPI.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SearchConfig {
    @Bean
    public ConcurrentHashMap<Long, User> getConcurrentHashMap(){
        return new ConcurrentHashMap<>();
    }
}
