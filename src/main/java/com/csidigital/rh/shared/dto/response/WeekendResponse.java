package com.csidigital.rh.shared.dto.response;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;

@Data
public class WeekendResponse {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String reference ;
    private String name;
    @Enumerated(EnumType.STRING)
    private DayOfWeek startDay;
    @Enumerated(EnumType.STRING)
    private DayOfWeek endDay;

}
