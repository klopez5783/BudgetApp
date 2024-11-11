package online.kristopherdev.BudgetPlanner.User;

public record Address(
        String Street,
        String Suite,
        String City,
        String Zipcode,
        Geo Geo
) {
}
