package online.kristopherdev.BudgetPlanner.User;

import online.kristopherdev.BudgetPlanner.Customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Component
public class UserRestClient {
    private static final Logger Log = LoggerFactory.getLogger(UserRestClient.class);

    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder){
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    public List<User> findAll(){
        try {
            List<User> users = restClient.get().uri("/users").retrieve().body(new ParameterizedTypeReference<List<User>>() {});
            Log.info("Fetched users: {}", users);
            return users;
        } catch (RestClientException e) {
            Log.error("Error fetching users", e);
            return null;
        }
    }

    public Customer findById(int ID){
        return restClient.get().uri("/users/{ID}", ID).retrieve().body(Customer.class);
    }

}