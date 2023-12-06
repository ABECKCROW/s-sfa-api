package com.abeck.ssfa.mapper;

import com.abeck.ssfa.controller.CompanyResponse;
import com.abeck.ssfa.entity.CompanyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

 @Mapper
 public interface CompanyMapper {
        @Select("SELECT companies.companies_id, company_name, company_phone, region, city, address, rank "
                + "WHERE company_name LIKE `%${companyName}%`"
                + "AND company_phone LIKE `%${companyPhone}%`"
                + "AND region LIKE `%${region}%`"
                + "AND city LIKE `%${city}%`"
                + "AND rank LIKE `%${rank}%`")
        List<CompanyResponse> findAllCompanies(String companyName,String companyPhone,String region,String city,String rank);

        @Select("SELECT * FROM companies WHERE id = #{companyId}")
        Optional<CompanyEntity> findCompanyById(int id);
 }

