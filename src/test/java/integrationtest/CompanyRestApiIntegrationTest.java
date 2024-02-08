package integrationtest;

import com.abeck.ssfa.SSfaApplication;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

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
                "companyPhone": "0312345678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S",
                "salesPersonId": 1
        },
        {
            "companyId": 2,
                "companyName": "Disk株式会社",
                "companyPhone": "0461234567",
                "region": "神奈川県",
                "city": "厚木市",
                "address": "2-2-2",
                "companyRank": "A",
                "salesPersonId": 2
        },
        {
            "companyId": 3,
                "companyName": "マウンテン株式会社",
                "companyPhone": "0460123456",
                "region": "神奈川県",
                "city": "箱根町",
                "address": "3-3-3",
                "companyRank": "B",
                "salesPersonId": 3
        },
        {
            "companyId": 4,
                "companyName": "シュガー株式会社",
                "companyPhone": "0467123456",
                "region": "神奈川県",
                "city": "綾瀬市",
                "address": "4-4-4",
                "companyRank": "C",
                "salesPersonId": 4
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
                "companyPhone": "0312345678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S",
                "salesPersonId": 1
        }
    ]
        """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void クエリパラメータの指定した電話番号と完全一致する企業情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/companies?companyPhone=0312345678"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
    [
        {
                "companyId": 1,
                "companyName": "ABECK株式会社",
                "companyPhone": "0312345678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S",
                "salesPersonId": 1
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
                "companyPhone": "0312345678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S",
                "salesPersonId": 1
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
                "companyPhone": "0312345678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S",
                "salesPersonId": 1
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
                "companyPhone": "0312345678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S",
                "salesPersonId": 1
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
                "companyPhone": "0312345678",
                "region": "東京都",
                "city": "千代田区",
                "address": "1-1-1",
                "companyRank": "S",
                "salesPersonId": 1
        }
        """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業検索で存在しないIDを指定したときに404エラーとなること() throws Exception {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        String timeStamp = currentDateTime.toString();
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/companies/99"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(String.format("""
            {
              "timestamp": "%s",
              "status": "404",
              "error": "Not Found",
              "message": "未登録の企業です。",
              "path": "/companies/99"
            }
        """, timeStamp), response, new CustomComparator(JSONCompareMode.STRICT, new Customization("timestamp", (o1, o2) -> true
        )));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @ExpectedDataSet(value = "datasets/expectedCreateCompanies.yml", ignoreCols = {"company_id"})
    @Transactional
    void 企業登録ができること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
        {
                "message": "企業が正常に登録されました。",
                "ID": 5
        }
        """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("ID", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで企業名がnullの時にバリデーションエラーとなること() throws Exception {
            String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content("""
                        {
                            "companyName": null,
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyName, message=企業名は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                    new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで電話番号がnullの時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": null,
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyPhone, message=電話番号は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで都道府県がnullの時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": null,
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=region, message=都道府県は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで市区町村がnullの時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": null,
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=city, message=市区町村は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで住所がnullの時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": null,
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=address, message=住所は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで企業ランクがnullの時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": null,
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyRank, message=企業ランクは入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで営業担当者IDがnullの時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": null
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=salesPersonId, message=営業担当者IDは入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで企業名が空文字の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyName, message=企業名は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで電話番号が空文字の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyPhone, message=電話番号の形式で入力してください。}, {field=companyPhone, message=電話番号は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで都道府県が空文字の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=region, message=2〜4文字以内で入力してください。}, {field=region, message=都道府県は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで市区町村が空文字の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=city, message=2〜6文字以内で入力してください。}, {field=city, message=市区町村は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで住所が空文字の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=address, message=住所は入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで企業ランクが空文字の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyRank, message=アルファベット大文字1文字で入力してください。}, {field=companyRank, message=企業ランクは入力必須です。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで営業担当者IDが空文字の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": ""
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=salesPersonId, message=営業担当者IDは入力必須です。}, {field=salesPersonId, message=自然数を入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで企業名が40文字以上の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわゐゑをん",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyName, message=40文字以内で入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで電話番号の始まりが0ではない時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "1234567890",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyPhone, message=電話番号の形式で入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで電話番号が番号ではない時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "aaaaaaaaaa",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyPhone, message=電話番号の形式で入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで都道府県が5文字以上の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "都道府県名",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=region, message=2〜4文字以内で入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで市区町村が7文字以上の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "日本市区町村名",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=city, message=2〜6文字以内で入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで住所が41文字以上の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわゐゑをん",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=address, message=40文字以内で入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで企業ランクが2文字以上の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "SA",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyRank, message=アルファベット大文字1文字で入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで企業ランクが小文字のアルファベットの時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "s",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyRank, message=アルファベット大文字1文字で入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録のリクエストで企業ランクがアルファベット以外の時にバリデーションエラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "未登録株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "あ",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "message": "[{field=companyRank, message=アルファベット大文字1文字で入力してください。}]",
                            "timestamp": "20231217T02:10:54.342233+09:00[Asia/Tokyo]",
                            "error": "Bad Request",
                            "path": "/companies",
                            "status": "400"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業登録で企業名と電話番号の組み合わせが存在する企業を登録したときに409エラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "ABECK株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "timestamp": "20231217T02:24:16.492008+09:00[Asia/Tokyo]",
                            "error": "Conflict",
                            "path": "/companies",
                            "status": "409",
                            "message": "すでに登録されている企業です。"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業更新で存在しないIDを指定したときに404エラーとなること() throws Exception {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        String timeStamp = currentDateTime.toString();
        String response = mockMvc.perform(MockMvcRequestBuilders.patch("/companies/99")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "companyName": "更新株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(String.format("""
                    {
                      "timestamp": "%s",
                      "status": "404",
                      "error": "Not Found",
                      "message": "未登録の企業です。",
                      "path": "/companies/99"
                    }
                """, timeStamp), response, new CustomComparator(JSONCompareMode.STRICT, new Customization("timestamp", (o1, o2) -> true
        )));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業更新で企業名と電話番号の組み合わせが存在する企業を更新したときに409エラーとなること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.patch("/companies/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "ABECK株式会社",
                            "companyPhone": "0312345678",
                            "region": "神奈川県",
                            "city": "川崎市",
                            "address": "高津区1-1-1",
                            "companyRank": "S",
                            "salesPersonId": "1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                        {
                            "timestamp": "20231217T02:24:16.492008+09:00[Asia/Tokyo]",
                            "error": "Conflict",
                            "path": "/companies/1",
                            "status": "409",
                            "message": "すでに登録されている企業です。"
                        }
                    """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("timestamp", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @ExpectedDataSet(value = "datasets/expectedUpdateCompanies.yml")
    @Transactional
    void 企業更新で存在するIDを指定したときに正常に企業情報が更新できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.patch("/companies/4")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "companyName": "更新後株式会社",
                            "companyPhone": "0982345678",
                            "region": "沖縄県",
                            "city": "那覇市",
                            "address": "44-44-44",
                            "companyRank": "S",
                            "salesPersonId": "4"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
        {
                "message": "企業情報が正常に更新されました。"
        }
        """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("ID", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    @ExpectedDataSet(value = "datasets/expectedDeleteCompanies.yml")
    void 企業削除で存在するIDを指定したときに正常に企業情報が削除できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/companies/4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
        {
                "message": "企業情報が正常に削除されました。"
        }
        """, response, new CustomComparator(JSONCompareMode.STRICT,
                new Customization("ID", (o1, o2) -> true)));
    }

    @Test
    @DataSet(value = "datasets/companies.yml")
    @Transactional
    void 企業削除で存在しないIDを指定したときに404エラーとなること() throws Exception {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        String timeStamp = currentDateTime.toString();
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/companies/99"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(String.format("""
                    {
                      "timestamp": "%s",
                      "status": "404",
                      "error": "Not Found",
                      "message": "未登録の企業です。",
                      "path": "/companies/99"
                    }
                """, timeStamp), response, new CustomComparator(JSONCompareMode.STRICT, new Customization("timestamp", (o1, o2) -> true
        )));
    }

}
