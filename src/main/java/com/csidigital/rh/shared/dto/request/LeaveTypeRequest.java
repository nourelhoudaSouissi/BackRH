package com.csidigital.rh.shared.dto.request;

import com.csidigital.rh.shared.enumeration.TimeOffType;
import lombok.*;

@Data
public class LeaveTypeRequest {
    private String name;
    private String description;
    private Integer duration;
    private TimeOffType timeOffType;

}
