package port.inbound;

import model.Transaction;

import java.util.List;

public interface ITransactionHistoryUseCase {
    List<Transaction> getTransactionHistory(Long idAccount);
}
