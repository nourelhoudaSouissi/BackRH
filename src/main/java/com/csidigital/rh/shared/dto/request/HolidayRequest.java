package com.csidigital.rh.shared.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
public class HolidayRequest {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer duration;
    private Long calendarNum;
}
