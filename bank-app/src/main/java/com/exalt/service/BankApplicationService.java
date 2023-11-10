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
    public Optional<Account> deposit(Long idAccount, BigDecimal amount) {
        Optional<Account> account = getAccountPort.get(idAccount);
        account.ifPresent(account1 ->  {
            account1.deposit(amount);
            saveAccountPort.save(account1);
        });
        return account;
    }

    @Override
    public Optional<Account> withdrawal(Long idAccount, BigDecimal amount) {
        Optional<Account> account = getAccountPort.get(idAccount);
        account.ifPresent(account1 ->  {
            account1.withdrawal(amount);
            saveAccountPort.save(account1);
        });

        return account;
    }
    @Override
    public List<Transaction> getTransactionHistory(Long idAccount) {
        Optional<Account> account = getAccountPort.get(idAccount);
        return account.orElseThrow(NoSuchElementException::new).getTransactions();
    }
}
