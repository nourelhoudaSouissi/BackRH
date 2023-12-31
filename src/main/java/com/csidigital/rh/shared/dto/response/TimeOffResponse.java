package com.csidigital.rh.shared.dto.response;

import com.csidigital.rh.dao.entity.Employee;
import com.csidigital.rh.shared.enumeration.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data

public class TimeOffResponse {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double timeOffPeriod;
    private String comment;
    private LocalDate requestInputDate;
    private RequestStatus requestStatus;
    private LocalDate validationDate;
    private ExternalTimeOffType externalTimeOffType;

    private Long leaveTypeId;

    private String leaveTypeName;

    private TimeOffType timeOffType;
    //private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String justificationDoc;
    @Enumerated(EnumType.STRING)
    private TimeOffPeriodType timeOffPeriodType;
    @Enumerated(EnumType.STRING)
    private TimeOfTimeType timeOfTimeType;


    private EmployeeResponse employee;
    private LeaveTypeResponse leaveType;

    private Integer leaveTypeAlertNumberDays;
    private Double remainingPaidLeave;  // New property for remainingPaidLeave

}
