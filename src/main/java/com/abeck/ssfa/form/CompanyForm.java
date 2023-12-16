package com.abeck.ssfa.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CompanyForm {

    @Size(max = 40, message = "{max}文字以内で入力してください。")
    @NotBlank(message = "企業名は入力必須です。")
    private String companyName;

    @Pattern(regexp = "^0\\d{9,11}$",
            message = "電話番号の形式で入力してください。")
    @NotBlank(message = "電話番号は入力必須です。")
    private String companyPhone;

    @Size(min = 2, max = 4, message = "{min}〜{max}文字以内で入力してください。")
    @NotBlank(message = "都道府県は入力必須です。")
    private String region;

    @Size(min = 2, max = 6, message = "{min}〜{max}文字以内で入力してください。")
    @NotBlank(message = "市区町村は入力必須です。")
    private String city;

    @Size(max = 40, message = "{max}文字以内で入力してください。")
    @NotBlank(message = "住所は入力必須です。")
    private String address;

    @Pattern(regexp = "^[A-Z]$", message = "アルファベット大文字1文字で入力してください。")
    @NotBlank(message = "企業ランクは入力必須です。")
    private String companyRank;

    @Pattern(regexp = "\\d+", message = "自然数を入力してください。")
    @NotBlank(message = "営業担当者IDは入力必須です。")
    private String salesPersonId;

    public int getSalesPersonIdInt() {
        return Integer.parseInt(this.salesPersonId);
    }
}
