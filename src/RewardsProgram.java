import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RewardsProgram {

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static Map<String, RewardsPerCustomer> rewards;

    public static void main(String[] args) throws ParseException {
        rewards = new HashMap<>();
        List<Transaction> transactionList = getTestData();
        calculateRewardsPerCustomer(rewards, transactionList);

        System.out.println(rewards);
    }

    private static void calculateRewardsPerCustomer(Map<String, RewardsPerCustomer> rewards,
                                                    List<Transaction> transactionList) {

        // Group the transactions based on customerId
        Map<String, List<Transaction>> transactionsPerPerson = transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId));

        for (Map.Entry<String, List<Transaction>> entry : transactionsPerPerson.entrySet()) {

            List<Transaction> personTransactions = entry.getValue();
            RewardsPerCustomer rewardsPerCustomer = new RewardsPerCustomer();

            rewardsPerCustomer.setTotalRewardsEarned(personTransactions.stream()
                    .map(transaction -> rewardsForAmount(transaction.getAmount())).reduce(0L, Long::sum));

            //Group the personTransactions based on month
            Map<String, List<Transaction>> transactionsPerPersonPerMonth = personTransactions.stream()
                    .collect(Collectors.groupingBy(Transaction::getTransactionMonth));

            Map<String, Long> rewardsPerMonthMap = new HashMap<>();
            for (Map.Entry<String, List<Transaction>> entry1 : transactionsPerPersonPerMonth.entrySet()) {
                List<Transaction> personTransactionsPerMonth = entry1.getValue();
                rewardsPerMonthMap.put(entry1.getKey(), personTransactionsPerMonth.stream()
                        .map(transaction -> rewardsForAmount(transaction.getAmount())).reduce(0L, Long::sum));
            }
            rewardsPerCustomer.setRewardsPerMonthMap(rewardsPerMonthMap);
            rewards.put(entry.getKey(), rewardsPerCustomer);
        }
    }

    private static long rewardsForAmount(long amount) {
        if (amount < 0) {
            LOGGER.log(Level.WARNING, "Transaction Amount cannot be less than 0");
            throw new RuntimeException("Invalid Transaction Amount"); // will throw exception depending on the requirement
        } else if (amount <= 50) {
            return 0;
        } else if (amount <= 100) {
            return amount - 50;
        } else {
            return 2 * (amount - 100) + 50;
        }
    }

    private static List<Transaction> getTestData() throws ParseException {
        List<Transaction> transactionList = new ArrayList<>();

        transactionList.add(new Transaction(20, "John", "October"));
        transactionList.add(new Transaction(80, "John", "October"));
        transactionList.add(new Transaction(192, "John", "November"));
        transactionList.add(new Transaction(90, "John", "November"));
        transactionList.add(new Transaction(780, "John", "December"));
        transactionList.add(new Transaction(12, "John", "December"));

        transactionList.add(new Transaction(30, "Kate", "October"));
        transactionList.add(new Transaction(100, "Kate", "October"));
        transactionList.add(new Transaction(232, "Kate", "November"));
        transactionList.add(new Transaction(76, "Kate", "November"));
        transactionList.add(new Transaction(21, "Kate", "December"));
        transactionList.add(new Transaction(150, "Kate", "December"));

        transactionList.add(new Transaction(50, "David", "October"));
        transactionList.add(new Transaction(143, "David", "October"));
        transactionList.add(new Transaction(34, "David", "November"));
        transactionList.add(new Transaction(76, "David", "November"));
        transactionList.add(new Transaction(543, "David", "December"));
        transactionList.add(new Transaction(476, "David", "December"));

        return transactionList;
    }
}
