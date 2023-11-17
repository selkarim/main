package port.inbound;

import model.Transaction;

import java.math.BigDecimal;
import java.util.Optional;

public interface IWithdrawalUseCase {
    Optional<Transaction> withdrawal(Long id, BigDecimal amount);
}
