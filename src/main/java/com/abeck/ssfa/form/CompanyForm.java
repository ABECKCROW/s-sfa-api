package com.abeck.ssfa.form;

import com.abeck.ssfa.Exception.InvalidSalesPersonIdException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CompanyForm {

    @Size(min = 1, max = 40, message = "{min}〜{max}文字以内で入力してください")
    @NotBlank(message ="企業名は入力必須です。")
    private String companyName;

    @Pattern(regexp = "^(0\\d{1,4}-\\d{1,4}-\\d{4}|^0\\d-\\d{4}-\\d{4}$|^0\\d{3}-\\d{2}-\\d{4}$|^(070|080|090)-\\d{4}-\\d{4}$|^050-\\d{4}-\\d{4}$|^0120-\\d{3}-\\d{3}$)",
            message = "電話番号の形式で入力してください")
    private String companyPhone;

    @Size(min = 2, max = 4, message = "{min}〜{max}文字以内で入力してください")
    @NotBlank(message ="都道府県は入力必須です。")
    private String region;

    @Size(min = 2, max = 6, message = "{min}〜{max}文字以内で入力してください")
    @NotBlank(message ="市区町村は入力必須です。")
    private String city;

    @Size(min = 1, max = 40, message = "{min}〜{max}文字以内で入力してください")
    @NotBlank(message ="住所は入力必須です。")
    private String address;

    @Size(min = 1, max = 1, message = "{min}文字で入力してください")
    private String companyRank;

    @Positive(message = "自然数を入力してください")
    private String salesPersonId;

    public int getSalesPersonIdInt(){
        try{
            return Integer.parseInt(this.salesPersonId);
        } catch (NumberFormatException e) {
            throw new InvalidSalesPersonIdException("数値を入力してください");
        }
    }
}
