package com.exalt.adapter;

import com.exalt.adapter.entity.AccountEntity;
import com.exalt.adapter.mappper.AccountJpaDomainMapper;
import com.exalt.adapter.repository.AccountRepository;
import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import port.outbound.GetAccountPort;
import port.outbound.SaveAccountPort;

import java.util.Optional;
@Component
public class AccountJpaAdapter implements SaveAccountPort, GetAccountPort {

    private final AccountRepository accountRepository;
    @Autowired
    public AccountJpaAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> get(Long id) {
        return Optional.ofNullable(AccountJpaDomainMapper
                .toDomain(accountRepository
                        .findById(id).orElseThrow()));
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = AccountJpaDomainMapper.toEntity(account);
        return AccountJpaDomainMapper
                .toDomain(accountRepository
                        .save(accountEntity));
    }

}
