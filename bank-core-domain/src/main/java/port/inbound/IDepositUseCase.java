package port.inbound;


import model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface IDepositUseCase {

    Optional<Account> deposit(Long idAccount, BigDecimal amount);
}
