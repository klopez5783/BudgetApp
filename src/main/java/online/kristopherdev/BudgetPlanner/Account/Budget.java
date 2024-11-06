package online.kristopherdev.BudgetPlanner.Account;

import java.math.BigDecimal;

public record Budget(String plan,
                     BigDecimal savings,
                     BigDecimal needs,
                     BigDecimal wants
) {
}
