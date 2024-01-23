package com.abeck.ssfa.mapper;

import com.abeck.ssfa.entity.CompanyEntity;
import org.apache.ibatis.annotations.*;

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
     Optional<CompanyEntity> findCompanyByNameAndPhone(String companyName, String companyPhone);

     @Insert("INSERT INTO companies (company_name, company_phone, region, city, address, company_rank, sales_person_id) VALUES (#{companyName}, #{companyPhone}, #{region}, #{city}, #{address}, #{companyRank}, #{salesPersonId})")
     @Options(useGeneratedKeys = true, keyColumn = "companyId", keyProperty = "companyId")
     void insertCompany(CompanyEntity companyEntity);

     @Update("UPDATE companies SET company_name = #{companyName}, company_phone = #{companyPhone}, region = #{region}, city = #{city}, address = #{address}, company_rank = #{companyRank}, sales_person_id = #{salesPersonId} WHERE company_id = #{companyId}")
     void updateCompany(int companyId, String companyName, String companyPhone, String region, String city, String address, String companyRank, int salesPersonId);
 }
