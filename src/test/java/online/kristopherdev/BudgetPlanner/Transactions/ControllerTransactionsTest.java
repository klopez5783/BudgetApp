package online.kristopherdev.BudgetPlanner.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ControllerTransactions.class)
class ControllerTransactionsTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper; //json to a type => vice versa

    @MockBean
    TransactionRepo repository;

    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        transactions = new ArrayList<>();
        transactions.add(new Transaction(3,
                3,
                3,
                new BigDecimal("15.00"),
                Transaction.Type.Income,
                new Date(),
                "Memo"));

        transactions.add(new Transaction(2,
                2,
                2,
                new BigDecimal("10.00"),
                Transaction.Type.Expense,
                new Date(),
                "Memo"));
    }

    @Test
    void shouldReturnAllTransactions() throws Exception {
        when(repository.findAll()).thenReturn(transactions);
        mvc.perform(MockMvcRequestBuilders.get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(transactions.size())));
    }

    @Test
    void shouldReturnTransactionById() throws Exception {
        Transaction transaction = transactions.getFirst();
        when(repository.findById(transaction.ID())).thenReturn(Optional.of(transaction));
        mvc.perform(MockMvcRequestBuilders.get("/api/transactions/{id}", transaction.ID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ID", is(transaction.ID())))
                .andExpect(jsonPath("$.Amount", is(transaction.Amount().doubleValue())));
    }

    @Test
    void shouldReturnNotFoundForInvalidTransactionId() throws Exception {
        when(repository.findById(99)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/transactions/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateTransaction() throws Exception {
        Transaction newTransaction = new Transaction(4, 4, 4, new BigDecimal("20.00"), Transaction.Type.Income, new Date(), "New Transaction");
        mvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newTransaction)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateTransaction() throws Exception {
        Transaction updatedTransaction = new Transaction(1, 1, 1, new BigDecimal("50.00"), Transaction.Type.Income, new Date(), "Updated Memo");
        when(repository.findById(updatedTransaction.ID())).thenReturn(Optional.of(transactions.getFirst()));
        mvc.perform(MockMvcRequestBuilders.put("/api/transactions/{id}", updatedTransaction.ID())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedTransaction)))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteTransaction() throws Exception {
        when(repository.findById(1)).thenReturn(Optional.of(transactions.getFirst()));
        mvc.perform(MockMvcRequestBuilders.delete("/api/transactions/1"))
                .andExpect(status().isNoContent());
    }

}