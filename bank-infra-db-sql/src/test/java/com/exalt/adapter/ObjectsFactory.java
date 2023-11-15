package com.exalt.adapter;

import com.exalt.adapter.entity.AccountEntity;
import com.exalt.adapter.entity.TransactionEntity;
import model.Account;
import model.Operation;
import model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class ObjectsFactory {
    private static final LocalDateTime CREATE_DATE_VALUE = LocalDateTime.of(2023,11,12,12,35,45);

    public static Account createAccountModel() {
        return Account.builder().idAccount(1L)
                .idCostumer(1L)
                .balance(BigDecimal.valueOf(8000))
                .transactions(new ArrayList<>())
                .build();
    }

    public static AccountEntity createAccountEntity() {
        return AccountEntity.builder()
                .id(1L)
                .idCostumer(1L)
                .balance(BigDecimal.valueOf(8000))
                .transactionEntities(List.of())
                .build();
    }
    public static Transaction createTransactionModel() {
        return new Transaction(BigDecimal.valueOf(250), CREATE_DATE_VALUE, Operation.DEPOSIT);
    }
    public static TransactionEntity createTransactionEntity() {
        return TransactionEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(250))
                .dateCreation(CREATE_DATE_VALUE)
                .operation(Operation.DEPOSIT)
                .build();
    }
}
