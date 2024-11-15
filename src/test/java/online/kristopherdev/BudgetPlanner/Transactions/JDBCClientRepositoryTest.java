package online.kristopherdev.BudgetPlanner.Transactions;

import online.kristopherdev.BudgetPlanner.BudgetPlannerApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JDBC_Client_Repository.class)
class JDBCClientRepositoryTest {
    @Autowired
    JDBC_Client_Repository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private List<Transaction> originalTransactions;

    @BeforeEach
    void setUp() {

        // Retrieve all transactions and store them in a list
        originalTransactions = repository.getTransactionsList();

        // Clear the Transaction and Budget tables
        jdbcTemplate.execute("DELETE FROM Transaction");

        // Insert transactions
        repository.create(new Transaction(
                1, // ID
                1, // AccountID
                3, // CategoryID
                new BigDecimal("5.00"), // Amount
                Transaction.Type.Income, // Type
                new Date(), // Date
                "memo" // Memo
        ));

        repository.create(new Transaction(
                2, // ID
                2, // AccountID
                4, // CategoryID
                new BigDecimal("50.00"), // Amount
                Transaction.Type.Expense, // Type
                new Date(), // Date
                "memo" // Memo
        ));


    }

    @AfterEach
    void tearDown() {
        // Clear the Transaction table
        jdbcTemplate.execute("DELETE FROM Transaction");

        // Repopulate the database with the original transactions
        repository.insertTransactions(originalTransactions);
    }

    @Test
    void getTransactionsList() {
        assertEquals(2, repository.getTransactionsList().size(), "Should return 2 transactions");
    }

    @Test
    void findByID() {
        Optional<Transaction> optionalTransaction = repository.findByID(1);
        assertTrue(optionalTransaction.isPresent(), "Transaction with ID 1 should be present");

        Transaction transaction = optionalTransaction.get();
        assertNotNull(transaction);
        assertEquals(1, transaction.ID());
        assertEquals(1, transaction.AccountID());
        assertEquals(3, transaction.CategoryID());
    }

    @Test
    void create() {
        Transaction transaction = new Transaction(
                3, // ID
                1, // AccountID
                5, // CategoryID
                new BigDecimal("60.00"), // Amount
                Transaction.Type.Income, // Type
                new Date(), // Date
                "memo" // Memo
        );

        repository.create(transaction);
        assertEquals(3, repository.getTransactionsList().size(), "Should return 3 transactions");
    }

    @Test
    void update() {
        Transaction updated = new Transaction(
                1, // ID
                2, // AccountID
                3, // CategoryID
                new BigDecimal("5.00"), // Amount
                Transaction.Type.Income, // Type
                new Date(), // Date
                "Updated" // Memo
        );

        repository.update(updated, 1);

        Optional<Transaction> optionalTransaction = repository.findByID(1);
        assertTrue(optionalTransaction.isPresent(), "Transaction with ID 1 should be present");

        Transaction transactionFromDb = optionalTransaction.get();

        assertEquals(updated, transactionFromDb, "The transaction updated should be the same as the one retrieved from the database");

        //assertEquals(2, repository.getTransactionsList().size(), "Should return 2 transactions");
    }

    @Test
    void remove() {
        repository.remove(1);
        assertEquals(1, repository.getTransactionsList().size(), "Should return 1 transaction");
    }

//    @Test
//    void insertTransactions() {
//        repository.insertTransactions(repository.getTransactionsList());
//        assertEquals(3, repository.getTransactionsList().size(), "Should return 3 transactions");
//    }
}