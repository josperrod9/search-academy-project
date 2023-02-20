package co.empathy.academy.searchAPI.services;

import co.empathy.academy.searchAPI.models.QueryResponse;
import org.springframework.stereotype.Service;

@Service
public interface SearchService {
    /*
    void makeAggsQuery() throws IOException;*/

    QueryResponse search(String query);
}
