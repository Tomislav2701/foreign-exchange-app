package com.tomi.fexapp.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = ConversionRecord.TABLE_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRecord {

    public static final String TABLE_NAME = "conversion_record";

    @Id
    @Column(name = "transaction_id", updatable = false, nullable = false)
    private UUID transactionId;

    @Column(name = "from_currency", nullable = false)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private String toCurrency;

    @Column(name = "exchange_rate", nullable = false)
    private BigDecimal exchangeRate;

    @Column(name = "original_amount", nullable = false)
    private BigDecimal originalAmount;

    @Column(name = "converted_amount", nullable = false)
    private BigDecimal convertedAmount;

    @Column(name = "conversion_timestamp", nullable = false,
        columnDefinition = "TIMESTAMP DEFAULT now()", insertable = false, updatable = false)
    private LocalDateTime conversionTimestamp;
}
