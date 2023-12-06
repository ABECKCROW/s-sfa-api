package com.abeck.ssfa.service;

import com.abeck.ssfa.controller.CompanyResponse;
import com.abeck.ssfa.entity.CompanyEntity;

import java.util.List;

public interface CompanyService {
    List<CompanyResponse> findAllCompanies(
            String companyName,String companyPhone,String region,String city,String rank);

    CompanyEntity findCompanyById(int companyId);
}
