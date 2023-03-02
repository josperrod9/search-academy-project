package co.empathy.academy.searchAPI.services;

import co.empathy.academy.searchAPI.configuration.ElasticSearchConfig;
import co.empathy.academy.searchAPI.models.QueryResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SearchServiceImpl implements SearchService {

    private final ElasticSearchConfig elasticSearchConfig;

    public SearchServiceImpl(ElasticSearchConfig elasticSearConfig) {
        this.elasticSearchConfig = elasticSearConfig;
    }

    /**
     * Makes a request from a .jar which have a function to obtain the cluster name and returns it and the query performed.
     *
     * @param query - query to search
     * @return QueryResponse
     */
    @Override
    public QueryResponse search(String query) throws IOException {
        return new QueryResponse(query, elasticSearchConfig.getClusterNameMethod().getClusterName());
    }
}
