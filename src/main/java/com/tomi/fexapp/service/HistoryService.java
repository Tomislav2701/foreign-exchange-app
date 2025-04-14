package com.tomi.fexapp.service;

import com.tomi.fexapp.dto.HistoryFilterDTO;
import com.tomi.fexapp.entity.ConversionRecord;
import com.tomi.fexapp.exception.MissingFilterException;
import com.tomi.fexapp.repository.ConversionRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HistoryService {

    private final ConversionRecordRepository recordRepository;

    public HistoryService(ConversionRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Page<ConversionRecord> getHistory(HistoryFilterDTO filter) {
        if (filter.getTransactionId() == null && filter.getDate() == null) {
            throw new MissingFilterException("At least one filter (transactionId or date) must be provided");
        }

        if (filter.getTransactionId() != null) {
            return recordRepository.findAll((root, query, cb) ->
                    cb.equal(root.get("transactionId"), filter.getTransactionId()),
                    PageRequest.of(filter.getPage(), filter.getSize()));
        } else {
            LocalDateTime start = filter.getDate().atStartOfDay();
            LocalDateTime end = filter.getDate().plusDays(1).atStartOfDay();
            return recordRepository.findAll((root, query, cb) ->
                    cb.between(root.get("conversionTimestamp"), start, end),
                    PageRequest.of(filter.getPage(), filter.getSize()));
        }
    }
}
