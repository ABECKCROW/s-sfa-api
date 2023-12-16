package com.abeck.ssfa.mapper;

import com.abeck.ssfa.entity.CompanyEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Optional;

 @Mapper
 public interface CompanyMapper {

     @SelectProvider(type = CompanySqlProvider.class, method = "SelectCompanyWithFilter")
     List<CompanyEntity> getCompanyWithFilter(
             @Param("companyName") String companyName,
             @Param("companyPhone") String companyPhone,
             @Param("region") String region,
             @Param("city") String city,
             @Param("companyRank") String companyRank
     );

     @Select("SELECT * FROM companies WHERE company_id = #{companyId}")
     Optional<CompanyEntity> findCompanyById(int companyId);

     @Select("SELECT * FROM companies WHERE company_name = #{companyName} AND company_phone = #{companyPhone}")
     Optional<CompanyEntity> isUniqueCompany(String companyName, String companyPhone);

     @Insert("INSERT INTO companies (company_name, company_phone, region, city, address, company_rank, sales_person_id) VALUES (#{companyName}, #{companyPhone}, #{region}, #{city}, #{address}, #{companyRank}, #{salesPersonId})")
     @Options(useGeneratedKeys = true, keyColumn = "companyId", keyProperty = "companyId")
     void insertCompany(CompanyEntity companyEntity);
 }
