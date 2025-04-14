package com.tomi.fexapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ExchangeRateRequestDTO {
    @NotEmpty(message = "From currency is required.")
    private String from;

    @NotEmpty(message = "To currency is required.")
    private String to;
}
