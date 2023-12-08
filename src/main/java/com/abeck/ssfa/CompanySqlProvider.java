package com.abeck.ssfa;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CompanySqlProvider {

    public String SelectCompanyWithFilter(
            @Param("companyName") String companyName,
            @Param("companyPhone") String companyPhone,
            @Param("region") String region,
            @Param("city") String city,
            @Param("rank") String rank
    ) {
        return new SQL() {{
            SELECT("*");
            FROM("companies");

            if (companyName != null && !companyName.isEmpty()) {
                WHERE("company_name LIKE CONCAT('%', #{companyName}, '%')");
            }

            if (companyPhone != null && !companyPhone.isEmpty()) {
                WHERE("company_phone LIKE CONCAT('%', #{companyPhone}, '%')");
            }

            if (region != null && !region.isEmpty()) {
                WHERE("region LIKE CONCAT('%', #{region}, '%')");
            }

            if (city != null && !city.isEmpty()) {
                WHERE("city LIKE CONCAT('%', #{city}, '%')");
            }

            if (rank != null && !rank.isEmpty()) {
                WHERE("rank LIKE CONCAT('%', #{rank}, '%')");
            }
        }}.toString();
    }
}
