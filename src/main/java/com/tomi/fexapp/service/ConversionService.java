package com.tomi.fexapp.service;

import com.tomi.fexapp.entity.ConversionRecord;
import com.tomi.fexapp.repository.ConversionRecordRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        BigDecimal converted = amount.multiply(rate);

        ConversionRecord record = new ConversionRecord();
        record.setTransactionId(UUID.randomUUID().toString());
        record.setFromCurrency(fromCurrency);
        record.setToCurrency(toCurrency);
        record.setExchangeRate(rate);
        record.setOriginalAmount(amount);
        record.setConvertedAmount(converted);
        record.setConversionTimestamp(LocalDateTime.now());

        return recordRepository.save(record);
    }
}
