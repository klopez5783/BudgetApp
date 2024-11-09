package online.kristopherdev.BudgetPlanner.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Component
public class TranactionsLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(TranactionsLoader.class);
    private final TransactionRepo repsoitory;
    private final ObjectMapper objectMapper;

    public TranactionsLoader(TransactionRepo repsoitory, ObjectMapper objectMapper) {
        this.repsoitory = repsoitory;
        this.objectMapper = objectMapper;
    }


    @Override
    public void run(String... args) throws Exception {

        if(repsoitory.findAll().isEmpty()){
            try{
                InputStream inputStream = TypeReference.class.getResourceAsStream("/data/transactions.json");
                Transactions transactions = objectMapper.readValue(inputStream, Transactions.class);
                log.info("Reading {} transactions", transactions.transactions().size());
                repsoitory.saveAll(transactions.transactions());
            }catch (IOException e){
                log.error("Error loading transactions: " + e.getMessage());
            }
        }

    }
}
