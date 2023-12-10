package integrationtest;

import com.abeck.ssfa.SSfaApplication;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = SSfaApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業情報が全件取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
    [
        {
                "companyId": 1,
                "companyName": "ABECK株式会社",
                "companyPhone": "03-1234-5678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S"
        },
        {
            "companyId": 2,
                "companyName": "Disk株式会社",
                "companyPhone": "046-123-4567",
                "region": "神奈川県",
                "city": "厚木市",
                "address": "2-2-2",
                "companyRank": "A"
        },
        {
            "companyId": 3,
                "companyName": "マウンテン株式会社",
                "companyPhone": "0460-12-3456",
                "region": "神奈川県",
                "city": "箱根町",
                "address": "3-3-3",
                "companyRank": "B"
        },
        {
            "companyId": 4,
                "companyName": "シュガー株式会社",
                "companyPhone": "0467-12-3456",
                "region": "神奈川県",
                "city": "綾瀬市",
                "address": "4-4-4",
                "companyRank": "C"
        }
    ]
        """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void クエリパラメータの指定した企業名と部分一致する企業情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/companies?companyName=ABECK"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
    [
        {
                "companyId": 1,
                "companyName": "ABECK株式会社",
                "companyPhone": "03-1234-5678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S"
        }
    ]
        """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void クエリパラメータの指定した電話番号と完全一致する企業情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/companies?companyPhone=03-1234-5678"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
    [
        {
                "companyId": 1,
                "companyName": "ABECK株式会社",
                "companyPhone": "03-1234-5678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S"
        }
    ]
        """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void クエリパラメータの指定した都道府県と完全一致する企業情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/companies?region=東京都"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
    [
        {
                "companyId": 1,
                "companyName": "ABECK株式会社",
                "companyPhone": "03-1234-5678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S"
        }
    ]
        """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void クエリパラメータの指定した市区町村と完全一致する企業情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/companies?city=千代田区"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
    [
        {
                "companyId": 1,
                "companyName": "ABECK株式会社",
                "companyPhone": "03-1234-5678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S"
        }
    ]
        """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void クエリパラメータの指定した企業ランクと完全一致する企業情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/companies?companyRank=S"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
    [
        {
                "companyId": 1,
                "companyName": "ABECK株式会社",
                "companyPhone": "03-1234-5678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S"
        }
    ]
        """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業検索で存在するIDを指定したときに正常に企業情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/companies/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
        {
                "companyId": 1,
                "companyName": "ABECK株式会社",
                "companyPhone": "03-1234-5678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S"
        }
        """, response, JSONCompareMode.STRICT);
    }
}
