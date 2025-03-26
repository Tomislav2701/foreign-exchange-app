package com.tomi.fexapp.controller;

import com.tomi.fexapp.entity.ConversionRecord;
import com.tomi.fexapp.service.ConversionService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/convert")
    public ConversionRecord convertCurrency(@RequestParam BigDecimal amount,
                                            @RequestParam String from,
                                            @RequestParam String to) {
        return conversionService.convertCurrency(amount, from, to);
    }
}
