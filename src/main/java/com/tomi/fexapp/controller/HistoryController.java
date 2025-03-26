package com.tomi.fexapp.controller;

import com.tomi.fexapp.entity.ConversionRecord;
import com.tomi.fexapp.repository.ConversionRecordRepository;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.*;

@RestController
@RequestMapping("/api")
public class HistoryController {

    private final ConversionRecordRepository recordRepository;

    public HistoryController(ConversionRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping("/history")
    public Page<ConversionRecord> getHistory(
            @RequestParam(required = false) String transactionId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (transactionId != null) {
            return recordRepository.findAll((root, query, cb) ->
                    cb.equal(root.get("transactionId"), transactionId), PageRequest.of(page, size));
        } else if (date != null) {
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            return recordRepository.findAll((root, query, cb) ->
                    cb.between(root.get("conversionTimestamp"), start, end), PageRequest.of(page, size));
        }
        return recordRepository.findAll(PageRequest.of(page, size));
    }
}
