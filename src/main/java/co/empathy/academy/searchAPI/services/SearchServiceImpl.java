package co.empathy.academy.searchAPI.services;

import co.empathy.academy.search.ClusterNameMethod;
import co.empathy.academy.searchAPI.configuration.ElasticSearchConfig;
import co.empathy.academy.searchAPI.models.QueryResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SearchServiceImpl implements SearchService {

    private final ClusterNameMethod clusterNameMethod;

    public SearchServiceImpl(ClusterNameMethod clusterNameMethod) {
        this.clusterNameMethod = clusterNameMethod;
    }

    /**
     * Makes a request from a .jar which have a function to obtain the cluster name and returns it and the query performed.
     *
     * @param query - query to search
     * @return QueryResponse
     */
    @Override
    public QueryResponse search(String query) throws IOException {
        return new QueryResponse(query, clusterNameMethod.getClusterName());
    }
}
