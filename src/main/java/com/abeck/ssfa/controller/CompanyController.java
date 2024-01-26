package com.abeck.ssfa.controller;

import com.abeck.ssfa.Exception.CompanyNotFoundException;
import com.abeck.ssfa.Exception.CompanyNotUniqueException;
import com.abeck.ssfa.entity.CompanyEntity;
import com.abeck.ssfa.form.CompanyForm;
import com.abeck.ssfa.service.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        return companyService.getCompanyWithFilter(companyName, companyPhone, region, city, companyRank);
    }

    @GetMapping("/{companyId}")
    public CompanyEntity findCompanyById(@PathVariable("companyId") int companyId) {
        return companyService.findCompanyById(companyId);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, String>> createCompany(
            @RequestBody @Validated CompanyForm companyForm) {
        int createdId = companyService.createCompany(companyForm.getCompanyName(), companyForm.getCompanyPhone(), companyForm.getRegion(), companyForm.getCity(), companyForm.getAddress(), companyForm.getCompanyRank(), companyForm.getSalesPersonIdInt());

        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/companies/" + createdId)
                .build()
                .toUri();
        String newId = String.valueOf(createdId);
        return ResponseEntity.created(url).body(Map.of("message", "企業が正常に登録されました。", "ID", newId));
    }

    @PatchMapping("/{company_id}")
    public ResponseEntity<Map<String,String>> updateCompany (
            @PathVariable("company_id") int companyId,
            @RequestBody @Validated CompanyForm companyForm) {
        companyService.updateCompany(companyId, companyForm.convertToCompany(companyId));
        return  ResponseEntity.ok(Map.of("message", "企業情報が正常に更新されました。"));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCompanyNotFoundException(
            CompanyNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompanyNotUniqueException.class)
    public ResponseEntity<Map<String, String>> handleCompanyNotUniqueException(
            CompanyNotUniqueException e, HttpServletRequest request) {
        Map<String ,String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.CONFLICT.value()),
                "error", HttpStatus.CONFLICT.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<Map<String, String>> errors = fieldErrors.stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new LinkedHashMap<>();
                    errorMap.put("field", fieldError.getField());
                    errorMap.put("message", Objects.requireNonNull(fieldError.getDefaultMessage()));
                    return errorMap;
                })
                .sorted(Comparator.comparing(m -> m.get("field") + m.get("message")))
                .toList();

        Map<String ,String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", errors.toString(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
