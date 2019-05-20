package com.example.demo;

import com.example.demo.company.Company;
import com.example.demo.company.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private CompanyRepository companyRepository;

    public DbSeeder(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Member jinsoo = new Member("jinsoo", "developer");
        Member kim = new Member("kim", "designer");

        Company wiseFashion = new Company(
                "Wisefashion",
                "서울시 중구 마장로 66",
                Arrays.asList(new Team("dev", 5, Arrays.asList(jinsoo))),
                Arrays.asList(jinsoo, kim)
        );

        this.companyRepository.deleteAll();

        List<Company> companies = Arrays.asList(wiseFashion);
        this.companyRepository.saveAll(companies);
    }
}
