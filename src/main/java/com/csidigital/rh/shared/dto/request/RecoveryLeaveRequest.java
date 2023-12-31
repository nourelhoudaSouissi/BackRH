package com.csidigital.rh.shared.dto.request;

import com.csidigital.rh.shared.enumeration.RecoveryDay;
import com.csidigital.rh.shared.enumeration.RecoveryHours;
import com.csidigital.rh.shared.enumeration.RecoveryType;
import com.csidigital.rh.shared.enumeration.RequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Data
public class RecoveryLeaveRequest {

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
    private Long holidayNum;
    private LocalDate weekendDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @Enumerated(EnumType.STRING)
    private RecoveryHours recoveryHours;

    private Long employeeNum;
    private String employeeFirstName;
    private String employeeLastName;


}
