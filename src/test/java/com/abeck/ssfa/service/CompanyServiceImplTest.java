package com.abeck.ssfa.service;

import com.abeck.ssfa.Exception.CompanyNotFoundException;
import com.abeck.ssfa.controller.CompanyResponse;
import com.abeck.ssfa.mapper.CompanyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @InjectMocks
    CompanyServiceImpl companyServiceImpl;

    @Mock
    CompanyMapper companyMapper;

    @Test
    public void 企業検索でfindAllCompaniesメソッドが呼び出されること() throws CompanyNotFoundException {
        List<CompanyResponse> companyResponse = List.of(
                new CompanyResponse(1,"株式会社ABECK","03-1234-5678","東京都","千代田区","1-1-1","S"));
                doReturn(companyResponse).when(companyMapper).findAllCompanies("株式会社ABECK","03-1234-5678","東京都","千代田区","S");

        List<CompanyResponse> actual = companyServiceImpl.findAllCompanies("株式会社ABECK","03-1234-5678","東京都","千代田区","S");
        assertThat(actual).isEqualTo(companyResponse);

        verify(companyMapper, times(1)).findAllCompanies("株式会社ABECK","03-1234-5678","東京都","千代田区","S");
    }
}
