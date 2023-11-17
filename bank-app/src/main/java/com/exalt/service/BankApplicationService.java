package com.exalt.service;

import model.Account;
import model.Transaction;
import port.inbound.IDepositUseCase;
import port.inbound.ITransactionHistoryUseCase;
import port.inbound.IWithdrawalUseCase;
import port.outbound.GetAccountPort;
import port.outbound.SaveAccountPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BankApplicationService implements IDepositUseCase, IWithdrawalUseCase, ITransactionHistoryUseCase {
    private final GetAccountPort getAccountPort;
    private final SaveAccountPort saveAccountPort;

    public BankApplicationService(GetAccountPort getAccountPort, SaveAccountPort saveAccountPort) {
        this.getAccountPort = getAccountPort;
        this.saveAccountPort = saveAccountPort;
    }

    @Override
    public Optional<Transaction> deposit(Long idAccount, BigDecimal amount) {
        Optional<Account> account = getAccountPort.get(idAccount);
        Optional<Transaction> transaction = Optional.empty();
        if(account.isPresent()) {
            transaction = account.get().deposit(amount);
            saveAccountPort.save(account.get());
        }
        return transaction;
    }

    @Override
    public Optional<Transaction> withdrawal(Long idAccount, BigDecimal amount) {
        Optional<Account> account = getAccountPort.get(idAccount);
        Optional<Transaction> transaction = Optional.empty();
        if (account.isPresent()) {
            transaction = account.get().withdrawal(amount);
            saveAccountPort.save(account.get());
        }
        return transaction;
    }
    @Override
    public List<Transaction> getTransactionHistory(Long idAccount) {
        Optional<Account> account = getAccountPort.get(idAccount);
        return account.orElseThrow(NoSuchElementException::new).getTransactions();
    }
    public Optional<BigDecimal> getBalance(Long idAccount) {
        return getAccountPort.get(idAccount).map(Account::getBalance);
    }
}
