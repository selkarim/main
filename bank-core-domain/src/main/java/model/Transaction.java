package model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(BigDecimal amount, LocalDateTime date, Operation operation) {}
