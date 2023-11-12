package com.exalt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import model.Operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@ToString
public class TransactionEntity {
    @Id
    private Long id;
    private BigDecimal amount;
    private LocalDateTime dateCreation;
    private Operation operation;
}