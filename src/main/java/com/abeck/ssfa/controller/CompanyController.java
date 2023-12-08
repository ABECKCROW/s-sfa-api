package com.abeck.ssfa.controller;

import com.abeck.ssfa.entity.CompanyEntity;
import com.abeck.ssfa.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("")
    public List<CompanyEntity> getCompanies(
                @RequestParam(value = "companyName", required = false) String companyName,
                @RequestParam(value = "companyPhone", required = false) String companyPhone,
                @RequestParam(value = "region", required = false) String region,
                @RequestParam(value = "city", required = false) String city,
                @RequestParam(value = "rank", required = false) String rank
        ) {
            return companyService.getCompanyWithFilter(companyName,companyPhone,region,city,rank);
    }

    @GetMapping("/{companyId}")
    public CompanyEntity findCompanyById(@PathVariable("companyId")int companyId) {
        return companyService.findCompanyById(companyId);
    }
}
