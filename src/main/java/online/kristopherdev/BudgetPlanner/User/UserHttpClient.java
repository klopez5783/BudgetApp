package online.kristopherdev.BudgetPlanner.User;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface UserHttpClient {

    @GetExchange("/users")
    List<User> findAll();

    @GetExchange("/users/{ID}")
    User findById(@PathVariable int ID);
}
