package com.csidigital.rh.shared.dto.request;

import com.csidigital.rh.shared.enumeration.Absence;
import com.csidigital.rh.shared.enumeration.Currency;
import com.csidigital.rh.shared.enumeration.FeeType;
import com.csidigital.rh.shared.enumeration.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ExpenseReportRequest {

    private LocalDate billingDate;
    @Enumerated(EnumType.STRING)
    private FeeType feeType;
    private double amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Boolean customerBilling;
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    private LocalDate createDate;
    private String comment;


    private Long employeeNum;
    private String employeeFirstName;
    private String employeeLastName;

}
