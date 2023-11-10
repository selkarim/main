package port.outbound;


import model.Account;

import java.util.Optional;

public interface GetAccountPort {
    Optional<Account> get(Long id);
}
