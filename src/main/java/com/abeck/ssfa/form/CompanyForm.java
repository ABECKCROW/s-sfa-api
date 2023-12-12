package com.abeck.ssfa.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CompanyForm {

    @Size(min = 1, max = 40, message = "1〜40文字以内で入力してください")
    @NotBlank(message ="企業名は入力必須です。")
    private String companyName;

    @Size(min =12, max = 12, message = "ハイフンを含めて12文字で入力してください")
    @NotBlank(message ="電話番号は入力必須です。")
    private String companyPhone;

    @Size(min = 2, max = 4, message = "2〜4文字以内で入力してください")
    @NotBlank(message ="都道府県は入力必須です。")
    private String region;

    @Size(min = 2, max = 6, message = "2〜6文字以内で入力してください")
    @NotBlank(message ="市区町村は入力必須です。")
    private String city;

    @Size(min = 1, max = 40, message = "1〜40文字以内で入力してください")
    @NotBlank(message ="住所は入力必須です。")
    private String address;

    @Size(min = 1, max = 1, message = "1文字で入力してください")
    private String companyRank;

    @Positive(message = "自然数を入力してください")
    private int salesPersonId;
}
