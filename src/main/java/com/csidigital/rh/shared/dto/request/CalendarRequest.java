package com.csidigital.rh.shared.dto.request;

import com.csidigital.rh.dao.entity.Holiday;
import lombok.Data;

import java.util.List;

@Data
public class CalendarRequest {
    private String reference ;
    private String name;
    private String description;

    private List<Holiday> holidays;
}
