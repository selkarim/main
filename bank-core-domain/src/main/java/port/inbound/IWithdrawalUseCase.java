package port.inbound;

import model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface IWithdrawalUseCase {
    Optional<Account> withdrawal(Long id, BigDecimal amount);
}
