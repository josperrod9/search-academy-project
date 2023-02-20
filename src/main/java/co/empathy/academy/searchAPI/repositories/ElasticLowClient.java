package co.empathy.academy.searchAPI.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface ElasticLowClient {
    String getElasticInfo();
}
