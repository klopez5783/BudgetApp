package online.kristopherdev.BudgetPlanner.Account;

import online.kristopherdev.BudgetPlanner.Transactions.Transaction;
import online.kristopherdev.BudgetPlanner.User.User;

import java.util.ArrayList;

public record Account(
        Budget budget,
        User user,
        String name,
        ArrayList<Transaction>transactions
        ) {
}
