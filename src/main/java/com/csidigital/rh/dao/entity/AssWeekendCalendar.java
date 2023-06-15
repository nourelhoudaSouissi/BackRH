package com.csidigital.rh.dao.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssWeekendCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDate activationStartDate;

    private LocalDate activationEndDate;


    @ManyToOne
    private Weekend weekend;

    @ManyToOne
    private Calendar calendar;

}
