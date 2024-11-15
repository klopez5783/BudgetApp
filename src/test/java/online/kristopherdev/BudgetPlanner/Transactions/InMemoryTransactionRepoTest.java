package online.kristopherdev.BudgetPlanner.Transactions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTransactionRepoTest {

    InMemoryTransactionRepo repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryTransactionRepo();
        repo.create(new Transaction(
                1, // ID
                2, // AccountID
                3, // CategoryID
                new BigDecimal("4.00"), // Amount
                Transaction.Type.Income, // Type
                new Date(), // Date
                "memo" // Memo
        ));
        repo.create(new Transaction(
                2, // ID
                3, // AccountID
                4, // CategoryID
                new BigDecimal("5.00"), // Amount
                Transaction.Type.Expense, // Type
                new Date(), // Date
                "memo" // Memo
        ));
    }

    @Test
    void findAll() {
        List<Transaction> transactions = repo.findAll();
        assertEquals(2, transactions.size() , "Should return 2 transactions");
    }

    @Test
    void testTransactionCreation() {
        Transaction transaction = new Transaction(
                1, // ID
                2, // AccountID
                3, // CategoryID
                new BigDecimal("4.00"), // Amount
                Transaction.Type.Income, // Type
                new Date(), // Date
                "memo" // Memo
        );

        assertNotNull(transaction);
        assertEquals(1, transaction.ID());
        assertEquals(2, transaction.AccountID());
        assertEquals(3, transaction.CategoryID());
        assertEquals(new BigDecimal("4.00"), transaction.Amount());
        assertEquals(Transaction.Type.Income, transaction.type());
        assertNotNull(transaction.date());
        assertEquals("memo", transaction.Memo());
    }

    @Test
    void testTransactionType() {
        Transaction transactionIncome = new Transaction(
                1, // ID
                2, // AccountID
                3, // CategoryID
                new BigDecimal("4.00"), // Amount
                Transaction.Type.Income, // Type
                new Date(), // Date
                "memo" // Memo
        );

        Transaction transactionExpense = new Transaction(
                2, // ID
                3, // AccountID
                4, // CategoryID
                new BigDecimal("5.00"), // Amount
                Transaction.Type.Expense, // Type
                new Date(), // Date
                "memo" // Memo
        );

        assertEquals(Transaction.Type.Income, transactionIncome.type());
        assertEquals(Transaction.Type.Expense, transactionExpense.type());
    }

}