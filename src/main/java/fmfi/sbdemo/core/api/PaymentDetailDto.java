package fmfi.sbdemo.core.api;

import fmfi.sbdemo.core.api.*;

@lombok.Builder
public record PaymentDetailDto(
        Money amount,
        java.time.LocalDate effectiveDate,
        String description
) {}
