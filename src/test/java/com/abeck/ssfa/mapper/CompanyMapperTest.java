package com.abeck.ssfa.mapper;

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
                        new CompanyEntity(1, "ABECK株式会社", "0312345678","東京都", "千代田区",  "1-1-1", "S", 1),
                        new CompanyEntity(2, "Disk株式会社", "0461234567","神奈川県", "厚木市",  "2-2-2", "A", 2),
                        new CompanyEntity(3, "マウンテン株式会社", "0460123456","神奈川県", "箱根町",  "3-3-3", "B", 3),
                        new CompanyEntity(4, "シュガー株式会社", "0467123456","神奈川県", "綾瀬市",  "4-4-4", "C", 4)
                );
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void クエリパラメータの指定した内容と部分または完全一致する企業情報が取得できること() {
        CompanyEntity companyEntity = new CompanyEntity(1, "ABECK株式会社", "0312345678","東京都", "千代田区",  "1-1-1", "S", 1);
        assertThat(companyMapper.getCompanyWithFilter("ABECK", null, null, null, null))
                .hasSize(1).contains(companyEntity);

        assertThat(companyMapper.getCompanyWithFilter(null, "0312345678", null, null, null))
                .hasSize(1).contains(companyEntity);

        assertThat(companyMapper.getCompanyWithFilter(null, null, "東京都", null, null))
                .hasSize(1).contains(companyEntity);

        assertThat(companyMapper.getCompanyWithFilter(null, null, null, "千代田区", null))
                .hasSize(1).contains(companyEntity);

        assertThat(companyMapper.getCompanyWithFilter(null, null, null, null, "S"))
                .hasSize(1).contains(companyEntity);
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業検索で存在するIDを指定したときに正常に企業情報が取得できること() {
        assertThat(companyMapper.findCompanyById(1))
                .contains(new CompanyEntity(1, "ABECK株式会社", "0312345678","東京都", "千代田区",  "1-1-1", "S", 1));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業検索で存在しないIDを指定したときに空のOptionalが取得されること() {
        assertThat(companyMapper.findCompanyById(99)).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業検索で指定した企業名と電話番号の組み合わせの企業情報が取得できること() {
        assertThat(companyMapper.isUniqueCompany("ABECK株式会社","0312345678")).contains(
                new CompanyEntity(1, "ABECK株式会社","0312345678", "東京都", "千代田区", "1-1-1", "S",1)
        );
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業検索で存在しない企業名と電話番号の組み合わせのときに空のOptionalが取得されること() {
        assertThat(companyMapper.isUniqueCompany("未登録株式会社", "0312345678")).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録ができること() {
        CompanyEntity companyEntity = new CompanyEntity(0, "未登録株式会社", "0312345678", "神奈川県", "川崎市", "高津区1-1-1" ,"S", 1);
        companyMapper.insertCompany(companyEntity);
        assertThat(companyEntity.getCompanyId()).isGreaterThan(4);
    }


}
