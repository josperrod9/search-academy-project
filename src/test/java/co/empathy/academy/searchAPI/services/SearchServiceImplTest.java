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
<<<<<<< HEAD
=======
import static org.mockito.BDDMockito.given;
>>>>>>> origin/main
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceImplTest {

    @Mock
    private ElasticLowClient elasticLowClient;

    @InjectMocks
    private SearchServiceImpl searchServiceImpl;

    @Test
<<<<<<< HEAD
    public void givenQuery_whenSearchInService_thenQueryResponse() throws IOException {
        // setup
        String query = "test query";
        String clusterName = "test-cluster";
        when(elasticLowClient.getClusterName()).thenReturn(clusterName);
=======
    public void testSearch() throws IOException {
        // setup
        String query = "test query";
        String clusterName = "test-cluster";
        given(elasticLowClient.getClusterName()).willReturn(clusterName);
>>>>>>> origin/main

        // execute
        QueryResponse response = searchServiceImpl.search(query);

<<<<<<< HEAD
        //verify
=======
>>>>>>> origin/main
        assertEquals(new QueryResponse(query, "test-cluster"), response);
    }
}
