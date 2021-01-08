import java.util.Date;

public class Transaction {
    private long amount;
    private String customerId;
    private String transactionMonth;

    public Transaction(long amount, String customerId, String transactionMonth) {
        this.amount = amount;
        this.customerId = customerId;
        this.transactionMonth = transactionMonth;
    }

    public Transaction() {
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getTransactionMonth() {
        return this.transactionMonth;
    }

    public long getAmount() {
        return this.amount;
    }
}
