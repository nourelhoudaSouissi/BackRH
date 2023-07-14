package com.csidigital.rh.dao.entity;

import com.csidigital.rh.shared.enumeration.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String lastName;
    @Column(name = "firstName")
    private String firstName;
    @Enumerated(EnumType.STRING)
    private Civility civility;
    @Enumerated(EnumType.STRING)
    private Title title;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    private LocalDate birthDate;
    private String emailOne;
    private String emailTwo;
    private Integer phoneNumberOne;
    private Integer phoneNumberTwo;
    private String address;
    private Integer postCode;
    private String city;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    private MaritalSituation maritalSituation;
    private Integer recommendationMark ;
    private Integer experience ;
    private String experienceDetails ;


    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;


    @Enumerated(EnumType.STRING)
    private WorkLocation workLocation;


    @Enumerated(EnumType.STRING)
    private Departement departement;

    private String serialNumber;

    // attribut qui désigne le congé de circoncision
    private Double circoncisionLeaveRest;

    // attribut qui désigne le congé de décés
    private Double deathLeaveRest;

    // attribut qui désigne le congé de marriage
    private Double marriageLeaveRest;

    // attribut qui désigne le congé de naissance
    private  Double maternityLeaveRest;

    // attribut qui désigne le rest des conjé payé
    private Double remainingPaidLeaveRest;

    // attribut qui désigne le total des conjé payé qui sera incrémenté chaque fin de mois de 1.83
    private Double remainingPaidLeave;

    // attribut qui désigne le rest des conjé  spécial payé
    private Double specialPaidLeaveRest;

    // attribut qui désigne le rest des conjé maladie (sickLeaveRest + compassioateLeaveRest "Accompagnement")
    private Double sicknessLeaveRest;

    // attribut qui désigne le rest des conjé maladie
    private Double sickLeaveRest;

    // attribut qui désigne le rest des conjé d'accompagnement
    private Double compassioateLeaveRest;

    // attribut qui désigne le rest des conjé de récupération
    private Double remainingRecoveryLeaveRest;

    // attribut qui désigne la date de recrutement de l'employee
    private LocalDate hireDate;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "technicalFileId")
    private TechnicalFile technicalFile;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<TimeOff> timeOffList;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<RecoveryLeave> recoveryLeaves;



    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "hierarchical_superior_id")
    private Employee hierarchicalSuperior;


}
