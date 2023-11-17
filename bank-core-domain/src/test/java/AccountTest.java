import model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;

class AccountTest {
    Account account;

    @BeforeEach
    void setUp() {
        account = Account.builder().idAccount(1L)
                .idCostumer(1L)
                .balance(BigDecimal.valueOf(8000))
                .transactions(new ArrayList<>())
                .build();
    }

    @Test
    void first_successful_deposit(){
        account.deposit(BigDecimal.valueOf(4555.555));
        Assertions.assertEquals(account.getBalance(), BigDecimal.valueOf(12555.555));
    }
    @ParameterizedTest
    @ValueSource(doubles = {200, 522, 500})
    void successful_deposit(double amount) {
        Assertions.assertNotNull(account.deposit(BigDecimal.valueOf(amount)));

    }
    @ParameterizedTest
    @ValueSource(doubles = {8001, -522})
    void illegal_withdrawal(double amount) {
       Assertions.assertThrows(IllegalArgumentException.class,
               () -> account.withdrawal(BigDecimal.valueOf(amount)),
               "Value not authorized de withdraw" );
    }
    @ParameterizedTest
    @ValueSource(doubles = {524, 10, 7999})
    void successful_withdrawal(double amount) {
        Assertions.assertNotNull(account.withdrawal(BigDecimal.valueOf(amount)));
    }


}