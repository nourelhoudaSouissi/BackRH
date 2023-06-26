package com.csidigital.rh.dao.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String reference ;
    private String name;
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<Holiday> holidays;

    @JsonManagedReference
    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<WeekendUpdated> weekendUpdateds;


}
