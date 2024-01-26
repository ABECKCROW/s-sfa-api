package com.abeck.ssfa.service;

import com.abeck.ssfa.entity.CompanyEntity;

import java.util.List;

public interface CompanyService {

    List<CompanyEntity> getCompanyWithFilter(String companyName, String companyPhone, String region, String city, String companyRank);

    CompanyEntity findCompanyById(int companyId);

    int createCompany(String companyName, String companyPhone, String region, String city, String address, String companyRank, int salesPersonId);

    void updateCompany(int companyId, CompanyEntity companyEntity);
}
