package com.exalt.service;

import model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import port.outbound.GetAccountPort;
import port.outbound.SaveAccountPort;

import javax.management.ReflectionException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankApplicationServiceTest {
    @InjectMocks
    BankApplicationService bankApplicationServiceUnderTest;

    @Mock
    GetAccountPort getAccountPortTest;
    @Mock
    SaveAccountPort saveAccountPortTest;


    @BeforeEach
    void setUp() {
        bankApplicationServiceUnderTest = new BankApplicationService(getAccountPortTest, saveAccountPortTest);
    }

    @ParameterizedTest
    @MethodSource(value = "accountArguments")
    void should_deposit(Account account) {

        //given
        when(getAccountPortTest.get(account.getIdAccount()))
                .thenReturn(Optional.of(account));

        //when

        Account actuelAccount = getAccountPortTest.get(account.getIdAccount()).orElseThrow();

        Account account1 = bankApplicationServiceUnderTest
                .deposit(actuelAccount.getIdAccount(), BigDecimal.valueOf(555)).orElseThrow();

        //then
        Assertions.assertEquals(account1.getBalance(), BigDecimal.valueOf(8555));

        verify(getAccountPortTest, times(2)).get(account.getIdAccount());
    }

    @Test
    void withdrawal() {
    }

    @Test
    void getTransactionHistory() {
    }

    private static Stream<Arguments> accountArguments() {
            return Stream.of(Arguments.of(Account.builder().idAccount(1L)
                            .idCostumer(1L)
                            .balance(BigDecimal.valueOf(8000))
                            .transactions(new ArrayList<>())
                            .build(), new IllegalArgumentException()));
    }
}