package com.csidigital.rh.shared.dto.request;

import com.csidigital.rh.shared.enumeration.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class TimeOffRequest {


    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double timeOffPeriod;
    private String comment;
    private LocalDate requestInputDate;
    private RequestStatus requestStatus;
    private LocalDate validationDate;
    private ExternalTimeOffType externalTimeOffType;
    private Long leaveTypeNum;
    private Long employeeNum;
    private String employeeFirstName;
    private String employeeLastName;
    private String justificationDoc;

    @Enumerated(EnumType.STRING)
    private TimeOffPeriodType timeOffPeriodType;
    @Enumerated(EnumType.STRING)
    private TimeOfTimeType timeOfTimeType;

}
