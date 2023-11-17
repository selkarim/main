package com.exalt.service;

import model.Account;
import model.Operation;
import model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import port.outbound.GetAccountPort;
import port.outbound.SaveAccountPort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

        Transaction transaction = bankApplicationServiceUnderTest
                .deposit(actuelAccount.getIdAccount(), BigDecimal.valueOf(555)).orElseThrow();

        //then
        Assertions.assertEquals(actuelAccount.getBalance(), BigDecimal.valueOf(8555));

        verify(getAccountPortTest, times(2)).get(account.getIdAccount());
    }

    @ParameterizedTest
    @MethodSource(value = "accountArguments")
    void should_withdrawal(Account account) {
        //given
        when(getAccountPortTest.get(account.getIdAccount()))
                .thenReturn(Optional.of(account));

        //when

        Account actuelAccount = getAccountPortTest.get(account.getIdAccount()).orElseThrow();

        Transaction transaction = bankApplicationServiceUnderTest
                .withdrawal(actuelAccount.getIdAccount(), BigDecimal.valueOf(555)).orElseThrow();

        //then
        Assertions.assertEquals(account.getBalance(), BigDecimal.valueOf(7445));

        verify(getAccountPortTest, times(2)).get(account.getIdAccount());
    }

    @ParameterizedTest
    @MethodSource(value = "accountArguments")
    void should_getTransactionHistory(Account account) {

        when(getAccountPortTest.get(account.getIdAccount())).thenReturn(Optional.of(account));

        account = getAccountPortTest.get(account.getIdAccount()).orElseThrow();

        List<Transaction> transactionList = List.of(
                bankApplicationServiceUnderTest
                .deposit(account.getIdAccount(), BigDecimal.valueOf(200)).orElseThrow(),
                bankApplicationServiceUnderTest
                        .deposit(account.getIdAccount(), BigDecimal.valueOf(300)).orElseThrow(),
                bankApplicationServiceUnderTest
                        .withdrawal(account.getIdAccount(), BigDecimal.valueOf(555)).orElseThrow()
        );


        List<Transaction> accountHistory = bankApplicationServiceUnderTest.getTransactionHistory(account.getIdAccount());
        Assertions.assertEquals(transactionList.size(), accountHistory.size());
        Assertions.assertEquals(transactionList.get(1), accountHistory.get(1));
        Assertions.assertEquals(accountHistory.get(0).operation(), Operation.DEPOSIT);
        Assertions.assertEquals(accountHistory.get(2).operation(), Operation.WITHDRAWAL);
    }

    private static Stream<Arguments> accountArguments() {
            return Stream.of(Arguments.of(Account.builder().idAccount(1L)
                            .idCostumer(1L)
                            .balance(BigDecimal.valueOf(8000))
                            .transactions(new ArrayList<>())
                            .build(), new IllegalArgumentException()));
    }
}