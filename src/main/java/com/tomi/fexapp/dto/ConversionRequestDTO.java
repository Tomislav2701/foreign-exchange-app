package com.tomi.fexapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ConversionRequestDTO {
    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    @NotEmpty(message = "Source currency is required")
    private String from;

    @NotEmpty(message = "Target currency is required")
    private String to;
}
