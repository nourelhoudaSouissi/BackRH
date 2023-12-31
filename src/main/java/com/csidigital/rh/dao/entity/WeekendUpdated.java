package com.csidigital.rh.dao.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekendUpdated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String reference ;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "startDay")
    private DayOfWeek startDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "endDay")
    private DayOfWeek endDay;

    private LocalDate activationStartDate;
    private LocalDate activationEndDate;
    @ManyToOne

    @JsonBackReference
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "calendarId")
    private Calendar calendar;
}
