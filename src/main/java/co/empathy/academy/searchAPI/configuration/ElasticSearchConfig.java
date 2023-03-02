package co.empathy.academy.searchAPI.configuration;


import co.empathy.academy.search.ClusterNameMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public ClusterNameMethod getClusterNameMethod() {
        return new ClusterNameMethod();
    }
}