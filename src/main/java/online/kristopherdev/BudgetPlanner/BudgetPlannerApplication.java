package online.kristopherdev.BudgetPlanner;

import online.kristopherdev.BudgetPlanner.Customer.Customer;
import online.kristopherdev.BudgetPlanner.Customer.CustomerRestClient;
import online.kristopherdev.BudgetPlanner.User.User;
import online.kristopherdev.BudgetPlanner.User.UserHttpClient;
import online.kristopherdev.BudgetPlanner.User.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
@EnableJdbcRepositories
public class BudgetPlannerApplication {

	private static final Logger Log = LoggerFactory.getLogger(BudgetPlannerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BudgetPlannerApplication.class, args);
		Log.info("Application Started Successfully!");

	}

	@Bean
	UserHttpClient userHttpClient(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}

	@Bean
	CommandLineRunner transaction(UserHttpClient client) {
		return args -> {
			List<User> list = client.findAll();
			System.out.println(list);
		};
	}
}
