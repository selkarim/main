package model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(Long idAccount,BigDecimal amount, LocalDateTime date, Operation operation) {}
