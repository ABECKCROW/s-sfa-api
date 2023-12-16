package com.abeck.ssfa.service;

import com.abeck.ssfa.Exception.CompanyNotFoundException;
import com.abeck.ssfa.Exception.CompanyNotUniqueException;
import com.abeck.ssfa.entity.CompanyEntity;
import com.abeck.ssfa.mapper.CompanyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
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
    public void 企業検索でgetCompanyWithFilterメソッドが呼び出されること() throws CompanyNotFoundException {
        List<CompanyEntity> companyEntity = List.of(
                new CompanyEntity(1,"株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1));
                doReturn(companyEntity).when(companyMapper).getCompanyWithFilter("株式会社ABECK","0312345678","東京都","千代田区","S");

        List<CompanyEntity> actual = companyServiceImpl.getCompanyWithFilter("株式会社ABECK","0312345678","東京都","千代田区","S");
        assertThat(actual).isEqualTo(companyEntity);

        verify(companyMapper, times(1)).getCompanyWithFilter("株式会社ABECK","0312345678","東京都","千代田区","S");
    }

    @Test
    public void 企業検索で存在するIDを指定したときに正常に企業情報が返されること() throws CompanyNotFoundException {
        doReturn(Optional.of(new CompanyEntity(1,"株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1))).when(companyMapper).findCompanyById(1);

        CompanyEntity actual = companyServiceImpl.findCompanyById(1);
        assertThat(actual).isEqualTo(new CompanyEntity(1,"株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1));
        verify(companyMapper, times(1)).findCompanyById(1);
    }

    @Test
    public void 企業検索で存在しないIDを指定したときに例外がスローされること() {
        doReturn(Optional.empty()).when(companyMapper).findCompanyById(99);

        assertThatThrownBy(() -> companyServiceImpl.findCompanyById(99)).isInstanceOfSatisfying(CompanyNotFoundException.class, e -> assertThat(e.getMessage()).isEqualTo("Company Not Found"));
        verify(companyMapper, times(1)).findCompanyById(99);
    }

    @Test
    public void 企業が登録できること() {
        CompanyEntity entity = new CompanyEntity(0, "株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1);
        CompanyEntity expectedCompany = new CompanyEntity(0,"株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1);
        doReturn(Optional.empty()).when(companyMapper).isUniqueCompany("株式会社ABECK","0312345678");
        doNothing().when(companyMapper).insertCompany(entity);

        int actual = companyServiceImpl.createCompany("株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1);
        assertThat(actual).isEqualTo(expectedCompany.getCompanyId());
        verify(companyMapper, times(1)).isUniqueCompany("株式会社ABECK","0312345678");
        verify(companyMapper, times(1)).insertCompany(entity);
    }

    @Test
    public void 企業登録で企業名と電話番号の組み合わせが存在する企業を登録したときに例外がスローされること() {
        CompanyEntity entity = new CompanyEntity(0, "株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1);
        CompanyEntity expectedCompany = new CompanyEntity(0,"株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1);
        doReturn(Optional.of(new CompanyEntity(0, "株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1))).when(companyMapper).isUniqueCompany("株式会社ABECK","0312345678");

        assertThatThrownBy(() -> companyServiceImpl.createCompany("株式会社ABECK","0312345678","東京都","千代田区","1-1-1","S",1)).isInstanceOfSatisfying(CompanyNotUniqueException.class, e -> assertThat(e.getMessage()).isEqualTo("すでに登録されている企業です。"));
        verify(companyMapper, times(1)).isUniqueCompany("株式会社ABECK","0312345678");
        verify(companyMapper, times(0)).insertCompany(entity);
    }
}
