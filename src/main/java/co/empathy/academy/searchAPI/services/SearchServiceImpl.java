package co.empathy.academy.searchAPI.services;

import co.empathy.academy.searchAPI.models.QueryResponse;
import co.empathy.academy.searchAPI.repositories.ElasticLowClient;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public QueryResponse search(String query) {
        String elasticInfo = elasticLowClient.getElasticInfo();
        //Parse the json above to obtain the cluster name
        String clusterName;
        try {
            clusterName = new JSONParser(elasticInfo).parseObject().get("cluster_name").toString();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new QueryResponse(query, clusterName);
    }
}
