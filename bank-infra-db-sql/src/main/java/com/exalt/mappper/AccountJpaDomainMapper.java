package com.exalt.mappper;
import com.exalt.entity.AccountEntity;
import model.Account;

public class AccountJpaDomainMapper {
    static AccountEntity  toEntity(Account account) {
        return AccountEntity
                .builder()
                .id(account.getIdAccount())
                .idCostumer(account.getIdCostumer())
                .balance(account.getBalance())
                .transactionEntities(TransactionJpaDomainMapper.toEntities(account.getTransactions()))
                .build();
    }
    static Account toDomain(AccountEntity entity) {
        return Account.builder()
                .idAccount(entity.getId())
                .idCostumer(entity.getIdCostumer())
                .balance(entity.getBalance())
                .transactions(entity.getTransactionEntities()
                        .stream()
                        .map(TransactionJpaDomainMapper::toDomain)
                        .toList()
                )
                .build();
    }
}