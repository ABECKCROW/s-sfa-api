package com.abeck.ssfa.controller;

import com.abeck.ssfa.Exception.CompanyNotFoundException;
import com.abeck.ssfa.entity.CompanyEntity;
import com.abeck.ssfa.service.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

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
                @RequestParam(value = "companyRank", required = false) String companyRank
        ) {
            return companyService.getCompanyWithFilter(companyName,companyPhone,region,city,companyRank);
    }

    @GetMapping("/{companyId}")
    public CompanyEntity findCompanyById(@PathVariable("companyId")int companyId) {
        return companyService.findCompanyById(companyId);
    }
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<Map<String, String >> handleCompanyNotFoundException(
            CompanyNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}

