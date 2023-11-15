package com.exalt.adapter.repository;

import com.exalt.adapter.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    @Override
    Optional<AccountEntity> findById(Long id);
}
