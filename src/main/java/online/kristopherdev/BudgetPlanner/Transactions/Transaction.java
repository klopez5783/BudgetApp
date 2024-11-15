package online.kristopherdev.BudgetPlanner.Transactions;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
        Type type,
        @NotNull(message = "Date cannot be null")
        Date date,
        String Memo
) {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        public enum Type {
                Income, Expense
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Transaction that = (Transaction) o;
                return ID == that.ID &&
                        AccountID == that.AccountID &&
                        CategoryID == that.CategoryID &&
                        Objects.equals(Amount, that.Amount) &&
                        type == that.type &&
                        Objects.equals(roundToSecond(date), roundToSecond(that.date)) && // Compare rounded dates
                        Objects.equals(Memo, that.Memo);
        }

        @Override
        public int hashCode() {
                return Objects.hash(ID, AccountID, CategoryID, Amount, type, roundToSecond(date), Memo);
        }

        public String formattedDate() {
                return dateFormat.format(date);
        }

        @Override
        public String toString() {
                return "Transaction[ID=" + ID + ", AccountID=" + AccountID + ", CategoryID=" + CategoryID + ", Amount=" + Amount + ", type=" + type + ", date=" + formattedDate() + ", Memo=" + Memo + "]";
        }

        private static Date roundToSecond(Date date) {
                return new Date((date.getTime() / 1000) * 1000);
        }
}