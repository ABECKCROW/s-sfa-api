package com.abeck.ssfa.mapper;

import com.abeck.ssfa.entity.CompanyEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

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

     @Select("SELECT * FROM companies WHERE company_name LIKE #{companyName} AND company_phone LIKE #{companyPhone}")
     Optional<CompanyEntity> findCompanyByNameAndPhone(String companyName, String companyPhone);

     @Insert("INSERT INTO companies (company_name, company_phone, region, city, address, company_rank, sales_person_id) VALUES (#{companyName}, #{companyPhone}, #{region}, #{city}, #{address}, #{companyRank}, #{salesPersonId})")
     @Options(useGeneratedKeys = true, keyColumn = "companyId", keyProperty = "companyId")
     void insertCompany(CompanyEntity companyEntity);

     @Update("UPDATE companies SET company_name = #{companyEntity.companyName}, company_phone = #{companyEntity.companyPhone}, region = #{companyEntity.region}, city = #{companyEntity.city}, address = #{companyEntity.address}, company_rank = #{companyEntity.companyRank}, sales_person_id = #{companyEntity.salesPersonId} WHERE company_id = #{companyId}")
     void updateCompany(int companyId, CompanyEntity companyEntity);

     @Delete("DELETE FROM companies WHERE company_id = #{companyId}")
     void deleteCompany(int companyId);
 }
