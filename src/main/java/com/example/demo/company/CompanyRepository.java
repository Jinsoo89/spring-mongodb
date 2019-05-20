package com.example.demo.company;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Company findCompanyByName(String name);
}
