package com.csidigital.rh.shared.dto.response;

import com.csidigital.rh.shared.enumeration.RecoveryDay;
import com.csidigital.rh.shared.enumeration.RecoveryHours;
import com.csidigital.rh.shared.enumeration.RecoveryType;
import com.csidigital.rh.shared.enumeration.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
public class RecoveryLeaveResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String name;

    private LocalDate date;

    private String comment;

    private Double duration;

    @Enumerated(EnumType.STRING)
    private RecoveryType recoveryType;
    private Integer  numberHours;
    @Enumerated(EnumType.STRING)
    private RecoveryDay recoveryDay;
    private  LocalDate inputDate;
    private LocalDate toTakeStartDate;
    private LocalDate toTakeEndDate;
    private Float periodToTake;

    private Long holidayId;
    private LocalDate weekendDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;


    @Enumerated(EnumType.STRING)
    private RecoveryHours recoveryHours;

    private Long employeeNum;
    private String employeeFirstName;
    private String employeeLastName;



}
