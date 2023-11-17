package port.inbound;


import model.Account;
import model.Transaction;

import java.math.BigDecimal;
import java.util.Optional;

public interface IDepositUseCase {

    Optional<Transaction> deposit(Long idAccount, BigDecimal amount);
}
