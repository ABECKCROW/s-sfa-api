package com.abeck.ssfa.mapper;

import com.abeck.ssfa.controller.CompanyResponse;
import com.abeck.ssfa.entity.CompanyEntity;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyMapperTest {

    @Autowired
    CompanyMapper companyMapper;

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void クエリパラメータの指定がないときにすべての企業情報が取得できること() {
        assertThat(companyMapper.getCompanyWithFilter(null, null, null, null, null))
                .hasSize(4).contains(
                        new CompanyEntity(1, "ABECK株式会社", "03-1234-5678","東京都", "千代田区",  "1-1-1", "S"),
                        new CompanyEntity(2, "Disk株式会社", "046-123-4567","神奈川県", "厚木市",  "2-2-2", "A"),
                        new CompanyEntity(3, "マウンテン株式会社", "0460-12-3456","神奈川県", "箱根町",  "3-3-3", "B"),
                        new CompanyEntity(4, "シュガー株式会社", "0467-12-3456","神奈川県", "綾瀬市",  "4-4-4", "C")
                );
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void クエリパラメータの指定した内容と部分または完全一致する企業情報が取得できること() {
        assertThat(companyMapper.getCompanyWithFilter("ABECK", null, null, null, null))
                .hasSize(1).contains(
                        new CompanyEntity(1, "ABECK株式会社", "03-1234-5678","東京都", "千代田区",  "1-1-1", "S"));
    }




}
