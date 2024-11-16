package online.kristopherdev.BudgetPlanner.Transactions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTransactionsIntegrationTest {


    @LocalServerPort
    int randomPort;

    RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.create("http://localhost:" + randomPort);
    }

    @Test
    void shouldReturnAllTransactions() {
        List<Transaction> List = restClient.get().uri("/api/transactions")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        assertEquals(5 , List.size());
    }

}