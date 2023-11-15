package com.exalt.adapter.mappper;

import com.exalt.adapter.entity.TransactionEntity;
import model.Transaction;

import java.util.List;

public class TransactionJpaDomainMapper {

    public static TransactionEntity toEntity(Transaction model) {
        return TransactionEntity.builder()
                .id((long) model.hashCode())
                .dateCreation(model.date())
                .amount(model.amount())
                .operation(model.operation())
                .build();
    }

    public static Transaction toDomain(TransactionEntity entity) {
        return new Transaction(entity.getAmount(), entity.getDateCreation(), entity.getOperation());
    }

    static List<TransactionEntity> toEntities(List<Transaction> transactionList){
        return transactionList.stream().map(TransactionJpaDomainMapper::toEntity).toList();
    }

}