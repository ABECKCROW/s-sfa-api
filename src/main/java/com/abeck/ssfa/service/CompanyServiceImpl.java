package com.abeck.ssfa.service;

import com.abeck.ssfa.Exception.CompanyNotFoundException;
import com.abeck.ssfa.Exception.CompanyNotUniqueException;
import com.abeck.ssfa.entity.CompanyEntity;

import com.abeck.ssfa.mapper.CompanyMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyEntity> getCompanyWithFilter(String companyName, String companyPhone, String region, String city, String companyRank) {
        return companyMapper.getCompanyWithFilter(companyName, companyPhone, region, city, companyRank);
    }

    @Override
    public CompanyEntity findCompanyById(int companyId) {
        return companyMapper.findCompanyById(companyId).orElseThrow(() -> new CompanyNotFoundException("未登録の企業です。"));
    }

    @Override
    public int createCompany(String companyName, String companyPhone, String region, String city, String address, String companyRank, int salesPersonId) {
        companyMapper.findCompanyByNameAndPhone(companyName, companyPhone).ifPresent(existingCompany -> {
            throw new CompanyNotUniqueException("すでに登録されている企業です。");
        });

        CompanyEntity newCompany = new CompanyEntity(0, companyName, companyPhone, region, city, address, companyRank, salesPersonId);
        companyMapper.insertCompany(newCompany);
        return newCompany.getCompanyId();
    }

    @Override
    public void updateCompany(int companyId, CompanyEntity companyEntity) {
        companyMapper.findCompanyById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("未登録の企業です。"));
        companyMapper.findCompanyByNameAndPhone(companyEntity.getCompanyName(), companyEntity.getCompanyPhone())
                .ifPresent(existingCompany -> {
                    throw new CompanyNotUniqueException("すでに登録されている企業です。");
                });
        companyMapper.updateCompany(companyId, companyEntity);
    }

    @Override
    public void deleteCompany(int companyId) {
        companyMapper.findCompanyById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("未登録の企業です。"));
        companyMapper.deleteCompany(companyId);
    }
}
