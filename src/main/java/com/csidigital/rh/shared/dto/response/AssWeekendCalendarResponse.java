package com.csidigital.rh.shared.dto.response;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssWeekendCalendarResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private LocalDate activationStartDate;

    private LocalDate activationEndDate;
}
