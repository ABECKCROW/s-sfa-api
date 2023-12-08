package com.abeck.ssfa.mapper;

import com.abeck.ssfa.CompanySqlProvider;
import com.abeck.ssfa.entity.CompanyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.beans.factory.annotation.Autowired;

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
             @Param("rank") String rank
     );

     @Select("SELECT * FROM companies WHERE company_id = #{companyId}")
     Optional<CompanyEntity> findCompanyById(int companyId);
 }
