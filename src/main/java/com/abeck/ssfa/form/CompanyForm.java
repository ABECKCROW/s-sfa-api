package com.abeck.ssfa.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class CompanyForm {

    @Size()
    @NotBlank(message ="企業名は入力必須です。")
    private String companyName;

    @Size(max = 40, message = "ハイフンを含めて12文字以内で入力してください")
    @NotBlank(message ="電話番号は入力必須です。")
    private String companyPhone;

    @Size(max = 4, message = "4文字以内で入力してください")
    @NotBlank(message ="都道府県は入力必須です。")
    private String region;

    @Size(max = 6, message = "6文字以内で入力してください")
    @NotBlank(message ="市区町村は入力必須です。")
    private String city;

    @Size(max = 40, message = "40文字以内で入力してください")
    @NotBlank(message ="住所は入力必須です。")
    private String address;

    @Size(max = 1, message = "1文字以内で入力してください")
    private String companyRank;

    private int salesPersonId;
}
