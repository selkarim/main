package com.exalt.adapter.mappper;

import com.exalt.adapter.ObjectsFactory;
import com.exalt.adapter.entity.AccountEntity;
import model.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountJpaDomainMapperTest {
    Account accountModel;
    AccountEntity accountEntity;
    @BeforeEach
    void setUp() {
        accountModel = ObjectsFactory.createAccountModel();
        accountEntity = ObjectsFactory.createAccountEntity();
    }


    @Test
    void toEntity() {
        Assertions.assertThat(accountEntity)
                .usingRecursiveComparison()
                .isEqualTo(AccountJpaDomainMapper.toEntity(accountModel));
    }

    @Test
    void toDomain() {
        Assertions.assertThat(accountModel)
                .usingRecursiveComparison()
                .isEqualTo(AccountJpaDomainMapper.toDomain(accountEntity));
    }

    @Test
    void toEntities() {
    }
}
