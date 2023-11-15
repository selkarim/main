package port.outbound;


import model.Account;

public interface SaveAccountPort {
    Account save(Account account);
}
