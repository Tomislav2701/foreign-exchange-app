package com.tomi.fexapp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ConversionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal exchangeRate;
    private BigDecimal originalAmount;
    private BigDecimal convertedAmount;
    private LocalDateTime conversionTimestamp;

    public ConversionRecord() {
    }

    public ConversionRecord(String transactionId, String fromCurrency, String toCurrency,
                            BigDecimal exchangeRate, BigDecimal originalAmount, BigDecimal convertedAmount,
                            LocalDateTime conversionTimestamp) {
        this.transactionId = transactionId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
        this.conversionTimestamp = conversionTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public LocalDateTime getConversionTimestamp() {
        return conversionTimestamp;
    }

    public void setConversionTimestamp(LocalDateTime conversionTimestamp) {
        this.conversionTimestamp = conversionTimestamp;
    }
}
