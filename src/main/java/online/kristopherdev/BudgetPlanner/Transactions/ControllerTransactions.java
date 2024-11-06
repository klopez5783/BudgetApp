package online.kristopherdev.BudgetPlanner.Transactions;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/transactions")
public class ControllerTransactions {

    private final TransactionsRepo repository;

    public ControllerTransactions(TransactionsRepo repo){
        this.repository = repo;
    }

    @GetMapping("")
    List<Transaction> getTransactions(){
        return repository.getTransactionsList();
    }

    @GetMapping("/{ID}")
    Transaction findById(@PathVariable Integer ID){
        Optional<Transaction> transaction = repository.findByID(ID);
        if(transaction.isEmpty()){
            throw new TransactionNotFoundException();
        }
        return transaction.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Transaction transaction){
        repository.create(transaction);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{ID}")
    void update(@Valid @RequestBody Transaction t , @PathVariable Integer ID ){
        repository.update(t,ID);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{ID}")
    void delete(@PathVariable Integer ID){
        repository.remove(ID);
    }


}
