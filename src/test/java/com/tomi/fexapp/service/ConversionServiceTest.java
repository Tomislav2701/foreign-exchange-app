package com.tomi.fexapp.service;

import com.tomi.fexapp.entity.ConversionRecord;
import com.tomi.fexapp.repository.ConversionRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class ConversionServiceTest {

    @Test
    public void testConvertCurrency() {
        ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);
        ConversionRecordRepository repository = Mockito.mock(ConversionRecordRepository.class);

        Mockito.when(exchangeRateService.getExchangeRate("USD", "EUR")).thenReturn(BigDecimal.valueOf(0.85));
        Mockito.when(repository.save(any(ConversionRecord.class))).thenAnswer(i -> i.getArguments()[0]);

        ConversionService conversionService = new ConversionService(exchangeRateService, repository);
        BigDecimal amount = BigDecimal.valueOf(100);

        ConversionRecord record = conversionService.convertCurrency(amount, "USD", "EUR");

        assertNotNull(record);
        assertTrue(BigDecimal.valueOf(85.0).compareTo(record.getConvertedAmount()) == 0,
                   "The converted amount should be numerically equal to 85.0");
        assertNotNull(record.getTransactionId());
    }
}
