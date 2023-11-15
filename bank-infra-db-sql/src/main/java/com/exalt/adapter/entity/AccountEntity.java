package com.exalt.adapter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@ToString
@Entity
public class AccountEntity {
    @Id
    private Long id;
    private Long idCostumer;
    private BigDecimal balance;
    @OneToMany
    private List<TransactionEntity> transactionEntities;

}