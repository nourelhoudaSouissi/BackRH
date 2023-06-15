package com.csidigital.rh.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "articleNumber")
    private Integer articleNumber;
    @Column(name = "articleTitle")
    private String articleTitle;
    @Column(name = "description" , length = 100000)
    private String description;



    @ManyToOne
    @JoinColumn(name = "id_contract")
    private Contract contract;

}
