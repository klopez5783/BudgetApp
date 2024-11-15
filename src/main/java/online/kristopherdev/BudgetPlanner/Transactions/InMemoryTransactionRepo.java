package online.kristopherdev.BudgetPlanner.Transactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryTransactionRepo {
    private static final Logger log = LoggerFactory.getLogger(InMemoryTransactionRepo.class);
    private final List<Transaction> transaction = new ArrayList<>();

    public List<Transaction> findAll(){
        return transaction;
    }

    public Optional<Transaction> findById(Integer ID){
        return Optional.ofNullable(transaction.stream()
                .filter(t -> t.ID() == ID)
                .findFirst()
                .orElseThrow(TransactionNotFoundException::new));
    }

    public void create(Transaction t){
        transaction.add(t);
    }

    public void update(Transaction t, Integer ID){
        transaction.set(ID, t);
    }

    public void delete(Integer ID){
        transaction.removeIf(t -> t.ID() == ID);
    }

    public void saveAll(List<Transaction> transactions){
        transactions.forEach(this::create);
    }

}
