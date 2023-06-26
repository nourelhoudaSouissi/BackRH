package com.csidigital.rh.shared.dto.request;

import com.csidigital.rh.dao.entity.Holiday;
import com.csidigital.rh.dao.entity.Weekend;
import com.csidigital.rh.dao.entity.WeekendUpdated;
import lombok.Data;

import java.util.List;

@Data
public class CalendarRequest {
    private String reference ;
    private String name;
    private String description;

    private List<Holiday> holidays;
    private List<WeekendUpdated> weekendUpdateds  ;
    //private List<Weekend> weekends  ;

}
