package port.outbound;


import model.Account;

public interface SaveAccountPort {
    void save(Account account);
}
