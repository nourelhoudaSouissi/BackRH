package com.csidigital.rh.shared.dto.response;


import com.csidigital.rh.shared.enumeration.TimeOffType;
import jakarta.persistence.*;
import lombok.*;

@Data
public class LeaveTypeResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String description;
    private Integer duration;
    private TimeOffType timeOffType;
}
