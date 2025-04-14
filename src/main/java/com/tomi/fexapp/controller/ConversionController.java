package com.tomi.fexapp.controller;

import com.tomi.fexapp.dto.ConversionRequestDTO;
import com.tomi.fexapp.entity.ConversionRecord;
import com.tomi.fexapp.service.ConversionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/convert")
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping
    public ConversionRecord convertCurrency(@Valid @ModelAttribute ConversionRequestDTO conversionRequestDTO) {
        return conversionService.convertCurrency(
            conversionRequestDTO.getAmount(),
            conversionRequestDTO.getFrom(),
            conversionRequestDTO.getTo()
        );
    }
}
