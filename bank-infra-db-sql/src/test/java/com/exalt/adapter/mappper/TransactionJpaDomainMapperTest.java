package com.exalt.adapter.mappper;

import com.exalt.adapter.ObjectsFactory;
import com.exalt.adapter.entity.TransactionEntity;
import model.Transaction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionJpaDomainMapperTest {

    Transaction transactionModel;
    TransactionEntity transactionEntity;
    @BeforeEach
    void setUp() {
        transactionModel = ObjectsFactory.createTransactionModel();
        transactionEntity = ObjectsFactory.createTransactionEntity();
    }


    @Test
    void toEntity() {
        Assertions.assertThat(transactionEntity)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(TransactionJpaDomainMapper.toEntity(transactionModel));
    }

    @Test
    void toDomain() {
        Assertions.assertThat(transactionModel)
                .usingRecursiveComparison()
                .isEqualTo(TransactionJpaDomainMapper.toDomain(transactionEntity));
    }

    @Test
    void toEntities() {
    }
}