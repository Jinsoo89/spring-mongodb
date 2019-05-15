package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/all")
    public List<Company> getAll() {
        List<Company> companies = this.companyRepository.findAll();

        return companies;
    }

    @PostMapping
    public void insert(@RequestBody Company company) {
        this.companyRepository.insert(company);
    }

    @PutMapping
    public void update(@RequestBody Company company) {
        this.companyRepository.save(company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.companyRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable("id") String id) throws Exception {
        Optional<Company> company = this.companyRepository.findById(id);

        if (company.isPresent()) {
            return company.get();
        } else {
            throw new Exception("There is no company with id: " + id);
        }
    }


}
