package com.csidigital.rh.shared.dto.request;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssWeekendCalendarRequest {


    private LocalDate activationStartDate;

    private LocalDate activationEndDate;
}
