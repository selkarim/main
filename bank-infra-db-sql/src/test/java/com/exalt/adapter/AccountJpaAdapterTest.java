package com.exalt.adapter;

import com.exalt.adapter.entity.AccountEntity;
import com.exalt.adapter.mappper.AccountJpaDomainMapper;
import com.exalt.adapter.repository.AccountRepository;
import model.Account;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.concurrent.atomic.LongAccumulator;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = AccountRepository.class)
public class AccountJpaAdapterTest {
    @InjectMocks
    AccountJpaAdapter accountJpaAdapter;
    @Mock
    AccountRepository accountRepository;
    Account accountActual;
    @Mock
    AccountEntity accountEntity;
    @BeforeEach
    void beforeEach() {

        accountActual = ObjectsFactory.createAccountModel();
        accountEntity = ObjectsFactory.createAccountEntity();
    }
    @AfterAll
    static void afterAll() {
    }

    @Test
    void get() {
        when(accountRepository.findById(accountActual.getIdAccount()))
                .thenReturn(Optional.of(AccountJpaDomainMapper.toEntity(accountActual)));

        AccountEntity accountEntity = accountJpaAdapter
                .get(accountActual.getIdAccount())
                .map(AccountJpaDomainMapper::toEntity).orElseThrow();

        Assertions.assertEquals(AccountJpaDomainMapper.toDomain(accountEntity).getBalance(), accountActual.getBalance());
        verify(accountRepository).findById(any(Long.class));

    }

    @Test
    void save() {
        doReturn(accountEntity).when(accountRepository).save(any());
        Account accountExpected = accountJpaAdapter.save(accountActual);
        Assertions.assertEquals(accountExpected.getIdAccount(), accountActual.getIdAccount());
        Assertions.assertEquals(accountExpected.getBalance(), accountActual.getBalance());
        verify(accountRepository, times(1)).save(any(AccountEntity.class));
    }
}