package com.example.demo.controller;

import com.example.demo.company.Company;
import com.example.demo.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public void insertCompanyAsBody(@RequestBody Company company) {
        companyService.addCompanyAsBody(company);
    }

//    @PostMapping
//    public void insertCompanyWithNameAndAddress(
//            @RequestParam String name, @RequestParam String address) throws Exception {
//
//        companyService.addCompanyWithNameAndAddr(name, address);
//    }

    @PutMapping
    public void update(@RequestBody Company company) {
        companyService.updateCompanyAsBody(company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        companyService.removeCompanyById(id);
    }

    @GetMapping("/all")
    public List<Company> getAll() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable("id") String id) throws Exception {
        return companyService.getCompanyById(id);
    }
}
