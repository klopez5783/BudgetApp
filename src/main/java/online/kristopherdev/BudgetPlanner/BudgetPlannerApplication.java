package online.kristopherdev.BudgetPlanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class BudgetPlannerApplication {

	private static final Logger Log = LoggerFactory.getLogger(BudgetPlannerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BudgetPlannerApplication.class, args);
		Log.info("Application Started Successfully!");

	}

//	@Bean
//	CommandLineRunner transaction(JDBC_Client_Repository repository){
//		return args -> {
//			Transaction transaction = new Transaction(
//					5,
//					2,
//					3,
//					new BigDecimal("20.00"),
//					"Type",
//					"Memo",
//					new Date() );
//            repository.create(transaction);
//		};
//	}
}
