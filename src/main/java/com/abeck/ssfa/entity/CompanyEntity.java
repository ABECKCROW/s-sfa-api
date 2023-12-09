package com.abeck.ssfa.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode

public class CompanyEntity {

    private int companyId;
    private String companyName;
    private String companyPhone;
    private String region;
    private String city;
    private String address;
    private String companyRank;
}
