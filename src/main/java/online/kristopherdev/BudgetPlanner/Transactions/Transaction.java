package online.kristopherdev.BudgetPlanner.Transactions;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import jakarta.persistence.Entity;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.util.Date;

public record Transaction(
        @Id
        int ID,
        @Column("AccountID")
        @NotNull(message = "AccountID cannot be null")
        int AccountID,
        @Column("CategoryID")
        @Min(value = 1, message = "CategoryID must be greater than or equal to 1")
        int CategoryID,
        @NotNull(message = "Amount cannot be null")
        @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01")
        BigDecimal Amount,
        @NotNull(message = "Type cannot be null")
        Type type,@NotNull(message = "Date cannot be null")
        Date date,
        String Memo
//        @Version
//        Integer version
) {
        public enum Type {
                Income, Expense
        }
}
