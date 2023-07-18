package com.csidigital.rh.shared.dto.response;

import com.csidigital.rh.shared.enumeration.FeeType;
import com.csidigital.rh.shared.enumeration.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ExpenseReportResponse {

    private Long Id;
    private LocalDate billingDate;
    @Enumerated(EnumType.STRING)
    private FeeType feeType;
    private double amount;
    private double currency;
    private Boolean customerBilling;
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    private LocalDate createDate;
    private String comment;
    private EmployeeResponse employee;
    private String employeeFirstName;
    private String employeeLastName;
}
