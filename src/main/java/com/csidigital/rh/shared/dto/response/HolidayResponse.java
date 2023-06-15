package com.csidigital.rh.shared.dto.response;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
public class HolidayResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer duration;

    private Long calendarId;
}
