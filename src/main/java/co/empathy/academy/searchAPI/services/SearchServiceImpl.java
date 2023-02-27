package co.empathy.academy.searchAPI.services;

import co.empathy.academy.searchAPI.models.QueryResponse;
import co.empathy.academy.searchAPI.repositories.ElasticLowClient;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.empathy.academy.search.ClusterNameMethod;

import java.io.IOException;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private final ElasticLowClient elasticLowClient;

    @Autowired
    public SearchServiceImpl(ElasticLowClient elasticLowClient) {
        this.elasticLowClient = elasticLowClient;
    }

    /**
     * Makes a request to obtain the cluster name and returns it and the query performed.
     *
     * @param query - query to search
     * @return QueryResponse
     */
    @Override
    public QueryResponse search(String query) throws IOException {
        return new QueryResponse(query, ClusterNameMethod.getClusterName());
    }
}
