package com.tomi.fexapp.service;

import com.tomi.fexapp.entity.ConversionRecord;
import com.tomi.fexapp.repository.ConversionRecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ConversionService {

    private final ExchangeRateService exchangeRateService;
    private final ConversionRecordRepository recordRepository;

    public ConversionService(ExchangeRateService exchangeRateService, 
                             ConversionRecordRepository recordRepository) {
        this.exchangeRateService = exchangeRateService;
        this.recordRepository = recordRepository;
    }

    public ConversionRecord convertCurrency(BigDecimal amount, String fromCurrency, String toCurrency) {
        BigDecimal rate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
        BigDecimal convertedAmount = amount.multiply(rate);

        ConversionRecord record = ConversionRecord.builder()
                .transactionId(UUID.randomUUID())
                .fromCurrency(fromCurrency)
                .toCurrency(toCurrency)
                .exchangeRate(rate)
                .originalAmount(amount)
                .convertedAmount(convertedAmount)
                .build();

        return recordRepository.save(record);
    }
}
