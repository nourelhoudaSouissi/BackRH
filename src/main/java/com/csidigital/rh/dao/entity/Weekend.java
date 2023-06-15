package com.csidigital.rh.dao.entity;


import com.csidigital.rh.shared.enumeration.TimeOffType;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weekend {
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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "AssWeekendCalendar",
            joinColumns = @JoinColumn(name = "calendar_id"),
            inverseJoinColumns = @JoinColumn(name = "weekend_id")
    )
    private List<Calendar> calendarList;

}
