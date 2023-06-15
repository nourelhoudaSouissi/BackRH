package com.csidigital.rh.shared.dto.request;

import com.csidigital.rh.dao.entity.TechnicalFile;
import com.csidigital.rh.shared.enumeration.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
@Data
public class EmployeeRequest {
    private String lastName;
    private String firstName;
    private Civility civility;
    private Title title;
    private EmployeeType employeeType;
    private LocalDate birthDate;
    private String emailOne;
    private String emailTwo;
    private Integer phoneNumberOne;
    private Integer phoneNumberTwo;
    private String address;
    private Integer postCode;
    private String city;
    private Country country;
    private MaritalSituation maritalSituation;
    private Integer recommendationMark ;
    private Integer experience ;
    private String experienceDetails ;
    @Enumerated(EnumType.STRING)
    private WorkLocation workLocation;

    @Enumerated(EnumType.STRING)
    private Provenance provenance;
    private String employeeFirstName;
    private String employeeLastName;
    private String EmployeeSerialNumber;
    private Long AssOfferCandidateId;

    private EmployeeStatus employeeStatus;
    private Departement departement;
    private TechnicalFile technicalFile ;

    private String serialNumber;


    private Double circoncisionLeaveRest;
    private Double deathLeaveRest;
    private Double marriageLeaveRest;
    private  Double maternityLeaveRest;
    private Double remainingPaidLeaveRest;
    private Double remainingPaidLeave;
    private Double specialPaidLeaveRest;
    private Double sicknessLeaveRest;
    private Double sickLeaveRest;
    private Double compassioateLeaveRest;
    private Double remainingRecoveryLeaveRest;
    private LocalDate hireDate;


}
