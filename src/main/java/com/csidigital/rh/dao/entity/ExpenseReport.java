package com.csidigital.rh.dao.entity;

import com.csidigital.rh.shared.enumeration.Absence;
import com.csidigital.rh.shared.enumeration.FeeType;
import com.csidigital.rh.shared.enumeration.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExpenseReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

}


