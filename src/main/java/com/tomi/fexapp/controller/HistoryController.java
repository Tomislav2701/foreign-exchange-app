package com.tomi.fexapp.controller;

import com.tomi.fexapp.dto.HistoryFilterDTO;
import com.tomi.fexapp.entity.ConversionRecord;
import com.tomi.fexapp.service.HistoryService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public Page<ConversionRecord> getHistory(@Valid @ModelAttribute HistoryFilterDTO filter) {
        return historyService.getHistory(filter);
    }
}
