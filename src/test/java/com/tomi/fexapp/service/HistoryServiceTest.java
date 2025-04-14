package com.tomi.fexapp.service;

import com.tomi.fexapp.dto.HistoryFilterDTO;
import com.tomi.fexapp.entity.ConversionRecord;
import com.tomi.fexapp.exception.MissingFilterException;
import com.tomi.fexapp.repository.ConversionRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class HistoryServiceTest {

    @Test
    public void testMissingFiltersThrowsException() {
        ConversionRecordRepository repository = Mockito.mock(ConversionRecordRepository.class);
        HistoryService historyService = new HistoryService(repository);
        
        HistoryFilterDTO filter = new HistoryFilterDTO();
        
        MissingFilterException exception = assertThrows(MissingFilterException.class, () -> {
            historyService.getHistory(filter);
        });
        assertEquals("At least one filter (transactionId or date) must be provided", exception.getMessage());
    }

    @Test
    public void testHistoryByTransactionId() {
        ConversionRecordRepository repository = Mockito.mock(ConversionRecordRepository.class);
        HistoryService historyService = new HistoryService(repository);

        UUID transactionId = UUID.randomUUID();
        ConversionRecord dummyRecord = ConversionRecord.builder()
                .transactionId(transactionId)
                .fromCurrency("USD")
                .toCurrency("EUR")
                .exchangeRate(BigDecimal.valueOf(0.85))
                .originalAmount(BigDecimal.valueOf(100))
                .convertedAmount(BigDecimal.valueOf(85))
                .build();

        Page<ConversionRecord> page = new PageImpl<>(Collections.singletonList(dummyRecord));
        Mockito.when(repository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(page);

        HistoryFilterDTO filter = new HistoryFilterDTO();
        filter.setTransactionId(transactionId);
        filter.setPage(0);
        filter.setSize(10);

        Page<ConversionRecord> result = historyService.getHistory(filter);
        assertFalse(result.isEmpty());
        assertEquals(transactionId, result.getContent().get(0).getTransactionId());
    }
}
