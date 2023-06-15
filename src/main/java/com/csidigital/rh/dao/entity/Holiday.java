package com.csidigital.rh.dao.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer duration;
    @OneToMany(mappedBy = "holiday", cascade = CascadeType.ALL)
    private List<RecoveryLeave> recoveryLeaveList;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "calendarId")
    private Calendar calendar;
}
