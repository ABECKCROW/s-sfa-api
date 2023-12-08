package com.abeck.ssfa.service;

import com.abeck.ssfa.Exception.CompanyNotFoundException;
import com.abeck.ssfa.entity.CompanyEntity;

import com.abeck.ssfa.mapper.CompanyMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyEntity> getCompanyWithFilter(String companyName, String companyPhone, String region, String city, String rank) {
        return companyMapper.getCompanyWithFilter(companyName,companyPhone,region,city,rank);
    }

    @Override
    public CompanyEntity findCompanyById(int companyId) {
        return companyMapper.findCompanyById(companyId).orElseThrow(() -> new CompanyNotFoundException("Company Not Found"));
    }
}
