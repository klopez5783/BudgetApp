package online.kristopherdev.BudgetPlanner.Transactions;

import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepo extends ListCrudRepository<Transaction, Integer> {
}
