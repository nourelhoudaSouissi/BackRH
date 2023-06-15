package com.csidigital.rh.shared.dto.response;

import com.csidigital.rh.dao.entity.Holiday;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class CalendarResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String reference ;
    private String name;
    private String description;
    private List<Holiday> holidays;

}
