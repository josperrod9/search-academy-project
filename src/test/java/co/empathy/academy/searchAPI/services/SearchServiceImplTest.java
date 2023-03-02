package co.empathy.academy.searchAPI.services;

import co.empathy.academy.search.ClusterNameMethod;
import co.empathy.academy.searchAPI.configuration.ElasticSearchConfig;
import co.empathy.academy.searchAPI.models.QueryResponse;
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
    private ClusterNameMethod clusterNameMethod;

    @InjectMocks
    private SearchServiceImpl searchServiceImpl;

    @Test
    public void givenQuery_whenSearchInService_thenQueryResponse() throws IOException {
        String query = "test query";
        String clusterName = "test-cluster";
        when(clusterNameMethod.getClusterName()).thenReturn(clusterName);


        // execute
        QueryResponse response = searchServiceImpl.search(query);

        //verify
        assertEquals(new QueryResponse(query, "test-cluster"), response);
    }
}
