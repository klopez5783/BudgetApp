package online.kristopherdev.BudgetPlanner.User;

public record User(
        int ID,
        String email,
        String password,
        String passwordHash
) {
}
