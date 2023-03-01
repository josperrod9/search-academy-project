package co.empathy.academy.searchAPI.services;


import co.empathy.academy.searchAPI.models.QueryResponse;
import co.empathy.academy.searchAPI.repositories.ElasticLowClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceImplTest {

    @Mock
    private ElasticLowClient elasticLowClient;

    @InjectMocks
    private SearchServiceImpl searchServiceImpl;

    @Test
    public void testSearch() throws IOException {
        // setup
        String query = "test query";
        String clusterName = "test-cluster";
        given(elasticLowClient.getClusterName()).willReturn(clusterName);

        // execute
        QueryResponse response = searchServiceImpl.search(query);

        assertEquals(new QueryResponse(query, "test-cluster"), response);
    }
}
