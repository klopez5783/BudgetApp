package online.kristopherdev.BudgetPlanner.Transactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBC_Client_Repository {

    private static final Logger Log = LoggerFactory.getLogger(JDBC_Client_Repository.class);
    private final JdbcClient jdbcClient;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JDBC_Client_Repository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Transaction> getTransactionsList(){
        return jdbcClient.sql("Select * from Transaction")
                .query(Transaction.class)
                .list();
    }

//    // Find a transaction by ID
//    public Optional<Transaction> findByID(Integer id) {
//        String sql = "SELECT * FROM Transaction WHERE ID = ?";
//        List<Transaction> transactions = jdbcTemplate.query(
//                con -> {
//                    PreparedStatement ps = con.prepareStatement(sql);
//                    ps.setInt(1, id);
//                    return ps;
//                },
//                (rs, rowNum) -> new Transaction(
//                        rs.getInt("ID"),
//                        rs.getInt("AccountID"),
//                        rs.getInt("CategoryID"),
//                        rs.getBigDecimal("Amount"),
//                        Transaction.Type.valueOf(rs.getString("Type")),
//                        rs.getDate("Date"),
//                        rs.getString("Memo")
//                )
//        );
//        if (transactions.isEmpty()) {
//            return Optional.empty();
//        } else {
//            return Optional.of(transactions.get(0));
//        }
//    }


    public Optional<Transaction> findByID(Integer id) {
        return jdbcClient.sql("SELECT * FROM Transaction WHERE ID = :id")
                .param("id", id)
                .query(Transaction.class)
                .optional();
    }

    // Create a new transaction
    public void create(Transaction t) {
        jdbcClient.sql("INSERT INTO Transaction (ID,AccountID, CategoryID, amount, type, Memo, date) VALUES (:ID,:accountId, :categoryId, :amount, :type, :memo, :date)")
                .param("ID", t.ID())
                .param("accountId", t.AccountID())
                .param("categoryId", t.CategoryID())
                .param("amount", t.Amount())
                .param("type", t.type().name())
                .param("memo", t.Memo())
                .param("date", t.date())
                .update();
    }

    // Update an existing transaction
    public void update(Transaction t, Integer id) {
        jdbcClient.sql("UPDATE Transaction SET AccountID = :accountId, CategoryID = :categoryId, amount = :amount, type = :type, Memo = :memo, date = :date WHERE ID = :id")
                .param("id", t.ID())
                .param("accountId", t.AccountID())
                .param("categoryId", t.CategoryID())
                .param("amount", t.Amount())
                .param("type", t.type().name())
                .param("memo", t.Memo())
                .param("date", t.date())
                .param("id", id)
                .update();
    }


    // Delete a transaction by ID
    public void remove(Integer id) {
        jdbcClient.sql("DELETE FROM Transaction WHERE ID = :id")
                .param("id", id)
                .update();
    }

    public void insertTransactions(List<Transaction> transactions) {
        transactions.forEach(this::create);
    }

//    private List<Transaction> transactionsList = new ArrayList<>();
//
//    List<Transaction> getTransactionsList(){
//        return transactionsList;
//    }
//
//    @PostConstruct
//    private void init(){
//        transactionsList.add(
//                new Transaction(1,
//                        1001,
//                        1,
//                        new BigDecimal("50.00"),
//                        "Expense",
//                        "Grocery Shopping",
//                        new Date())
//        );
//        transactionsList.add(new Transaction(2,
//                1002,
//                2,
//                new BigDecimal("70.00"),
//                "Expense",
//                "Phone Bill",
//                new Date())
//        );
//    }
//
//    Optional<Transaction> findByID(Integer id){
//        return transactionsList.stream().filter(transactions -> transactions.ID() == id).findFirst();
//    }
//
//    void create(Transaction t){
//        transactionsList.add(t);
//    }
//
//    void update(Transaction t , Integer ID){
//        Optional<Transaction> existing_T = findByID(ID);
//        existing_T.ifPresent(transactions -> transactionsList.set(transactionsList.indexOf(transactions), t));
//    }
//
//    void remove(Integer ID){
//        transactionsList.removeIf( transactions -> transactions.ID() == ID );
//    }

}
