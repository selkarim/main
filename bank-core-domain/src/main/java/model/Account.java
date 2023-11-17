package model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Account {
    private final Long idAccount;
    private final Long idCostumer;
    private BigDecimal balance;
    private List<Transaction> transactions;

    private Account(AccountBuilder builder) {
        this.idAccount = builder.idAccount;
        this.idCostumer = builder.idCostumer;
        this.balance = builder.balance;
        this.transactions = builder.transactions;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public Long getIdCostumer() {
        return idCostumer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Optional<Transaction> deposit(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new IllegalArgumentException("Value not authorized to deposit");
        }
        Transaction transaction = new Transaction(getIdAccount(),amount, LocalDateTime.now(),Operation.DEPOSIT);
        transactions.add(transaction);
        balance = balance.add(amount);
        return Optional.ofNullable(transaction);
    }
    public Optional<Transaction> withdrawal(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) < 0 || balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Value not authorized to withdraw");
        }
        Transaction transaction = new Transaction(getIdAccount(),amount, LocalDateTime.now(), Operation.WITHDRAWAL);
        transactions.add(transaction);
        balance = balance.subtract(amount);
        return Optional.ofNullable(transaction);

    }
    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public static class AccountBuilder {
        private Long idAccount;
        private Long idCostumer;
        private BigDecimal balance;
        private List<Transaction> transactions;
        public AccountBuilder idAccount(Long idAccount) {
            this.idAccount = idAccount;
            return this;
        }
        public AccountBuilder idCostumer(Long idCostumer) {
            this.idCostumer = idCostumer;
            return this;
        }
        public AccountBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }
        public AccountBuilder transactions(List<Transaction> transactions) {
            if(this.transactions == null) {
                this.transactions = new ArrayList<>();
            }
            this.transactions.addAll(transactions);
            return this;
        }
        public Account build() {
            return new Account(this);
        }

    }
}