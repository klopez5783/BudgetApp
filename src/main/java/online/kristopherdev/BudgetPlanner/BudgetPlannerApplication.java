package online.kristopherdev.BudgetPlanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BudgetPlannerApplication {

	private static final Logger Log = LoggerFactory.getLogger(BudgetPlannerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BudgetPlannerApplication.class, args);
		Log.info("Application Started Successfully!");

	}

//	@Bean
//	CommandLineRunner transaction(TransactionsRepo repository){
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
