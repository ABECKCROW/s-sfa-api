package com.abeck.ssfa.service;

import com.abeck.ssfa.entity.CompanyEntity;
import com.abeck.ssfa.form.CompanyForm;

import java.util.List;

public interface CompanyService {

    List<CompanyEntity> getCompanyWithFilter(String companyName, String companyPhone, String region, String city, String companyRank);

    CompanyEntity findCompanyById(int companyId);

    int createCompany(String companyName, String companyPhone, String region, String city, String address, String companyRank, int salesPersonId);

    CompanyEntity updateCompany(int companyId, String companyName, String companyPhone, String region, String city, String address, String companyRank, int salesPersonId);
}
