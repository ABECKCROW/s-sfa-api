package com.abeck.ssfa.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyResponse {

    private int companyId;
    private String companyName;
    private String companyPhone;
    private String region;
    private String city;
    private String address;
    private String companyRank;
    private int salesPersonId;
}
