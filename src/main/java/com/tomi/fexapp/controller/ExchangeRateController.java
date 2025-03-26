package com.tomi.fexapp.controller;

import com.tomi.fexapp.service.ExchangeRateService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/exchange-rate")
    public BigDecimal getExchangeRate(@RequestParam String from, @RequestParam String to) {
        return exchangeRateService.getExchangeRate(from, to);
    }
}
