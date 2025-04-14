package com.tomi.fexapp.controller;

import com.tomi.fexapp.dto.ExchangeRateRequestDTO;
import com.tomi.fexapp.service.ExchangeRateService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange-rate")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    public BigDecimal getExchangeRate(@RequestBody @Valid ExchangeRateRequestDTO exchangeRateRequestDTO) {
        return exchangeRateService.getExchangeRate(
                exchangeRateRequestDTO.getFrom(),
                exchangeRateRequestDTO.getTo()
        );
    }
}
