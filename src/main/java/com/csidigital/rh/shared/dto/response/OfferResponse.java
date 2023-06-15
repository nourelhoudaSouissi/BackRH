package com.csidigital.rh.shared.dto.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.util.Set;

@Data
public class OfferResponse {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private String title;
    private String reference;
    private Set<Long> candidate;

}
