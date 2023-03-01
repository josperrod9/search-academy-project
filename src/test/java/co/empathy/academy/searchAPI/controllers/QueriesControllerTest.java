package co.empathy.academy.searchAPI.controllers;

import co.empathy.academy.searchAPI.models.QueryResponse;
import co.empathy.academy.searchAPI.services.SearchService;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QueriesControllerTest {
    private final SearchService service = mock(SearchService.class);
    private final int EXPECTED_SUCCESS_STATUS = 200;
    private final int EXPECTED_ERROR_STATUS = 500;
    private final String query = "query";


    @Test
    void givenQuery_whenSearch_thenQueryResponse() throws IOException {
        String query = "Query";
        QueryResponse response = new QueryResponse(query, "clusterName");

        given(service.search(query)).willReturn(response);

        QueriesController queriesController = new QueriesController(service);

        ResponseEntity<QueryResponse> result = queriesController.search(query);
        System.out.printf(String.valueOf(response));
        assertEquals(response, result.getBody());
    }
}
