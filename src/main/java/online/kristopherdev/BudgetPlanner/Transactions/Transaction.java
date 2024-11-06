package online.kristopherdev.BudgetPlanner.Transactions;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record Transaction(
        int ID,
        int BudgetID,
        @Min(value = 1, message = "Category must be greater than or equal to 1")
        int Category,
        @NotNull(message = "Amount cannot be null")
        @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01")
        BigDecimal amount,
        String type,
        String Memo,
        @NotNull(message = "Date cannot be null")
        Date date
) {

    //validate input data.


}
