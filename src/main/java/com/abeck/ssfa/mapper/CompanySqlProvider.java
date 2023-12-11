package com.abeck.ssfa.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Objects;

public class CompanySqlProvider {

    public String SelectCompanyWithFilter(
            @Param("companyName") String companyName,
            @Param("companyPhone") String companyPhone,
            @Param("region") String region,
            @Param("city") String city,
            @Param("companyRank") String companyRank
    ) {
        return new SQL() {{
            SELECT("*");
            FROM("companies");

            if (Objects.nonNull(companyName) && !companyName.isEmpty()) {
                WHERE("company_name LIKE CONCAT('%', #{companyName}, '%')");
            }

            if (Objects.nonNull(companyPhone) && !companyPhone.isEmpty()) {
                WHERE("company_phone LIKE CONCAT('%', #{companyPhone}, '%')");
            }

            if (Objects.nonNull(region) && !region.isEmpty()) {
                WHERE("region LIKE CONCAT('%', #{region}, '%')");
            }

            if (Objects.nonNull(city) && !city.isEmpty()) {
                WHERE("city LIKE CONCAT('%', #{city}, '%')");
            }

            if (Objects.nonNull(companyRank) && !companyRank.isEmpty()) {
                WHERE("company_rank LIKE CONCAT('%', #{companyRank}, '%')");
            }
        }}.toString();
    }
}
