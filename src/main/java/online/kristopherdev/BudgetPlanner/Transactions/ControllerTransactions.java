package online.kristopherdev.BudgetPlanner.Transactions;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/transactions")
public class ControllerTransactions {

    private final TransactionRepo repository;

    public ControllerTransactions(TransactionRepo repo){
        this.repository = repo;
    }

    @GetMapping("")
    List<Transaction> getTransactions(){
        return repository.findAll();
    }

    @GetMapping("/{ID}")
    Transaction findById(@PathVariable Integer ID){
        Optional<Transaction> transaction = repository.findById(ID);
        if(transaction.isEmpty()){
            throw new TransactionNotFoundException();
        }
        return transaction.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Transaction transaction){
        repository.save(transaction);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{ID}")
    void update(@Valid @RequestBody Transaction t , @PathVariable Integer ID ){
        repository.save(t);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{ID}")
    void delete(@PathVariable Integer ID){
        repository.delete(repository.findById(ID).get());
    }


}
