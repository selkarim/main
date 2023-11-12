package com.exalt.mappper;

import com.exalt.entity.TransactionEntity;
import model.Transaction;

import java.util.List;

public class TransactionJpaDomainMapper {

    static TransactionEntity toEntity(Transaction model) {
        return TransactionEntity.builder()
                .id((long) model.hashCode())
                .dateCreation(model.date())
                .amount(model.amount())
                .operation(model.operation())
                .build();
    }

    static Transaction toDomain(TransactionEntity entity) {
        return new Transaction(entity.getAmount(), entity.getDateCreation(), entity.getOperation());
    }

    static List<TransactionEntity> toEntities(List<Transaction> transactionList){
        return transactionList.stream().map(TransactionJpaDomainMapper::toEntity).toList();
    }

}