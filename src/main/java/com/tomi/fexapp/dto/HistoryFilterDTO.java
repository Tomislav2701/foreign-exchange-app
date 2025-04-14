package com.tomi.fexapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class HistoryFilterDTO {
    private UUID transactionId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Schema(defaultValue = "0")
    @Min(value = 0, message = "Page index must be non-negative")
    private Integer page = 0;

    @Schema(defaultValue = "10")
    @Min(value = 1, message = "Size must be at least 1")
    private Integer size = 10;

    @AssertTrue(message = "At least one filter (transactionId or date) must be provided")
    public boolean isAtLeastOneFilterProvided() {
         return transactionId != null || date != null;
    }
}
