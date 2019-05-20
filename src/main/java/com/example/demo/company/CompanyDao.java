package com.example.demo.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyDao {
    public static final String COLLECTION = "companies";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CompanyRepository companyRepository;

    public Company insertCompanyAsBody(Company company) {
        return companyRepository.insert(company);
    }

    public Company updateCompanyAsBody(Company company) {
        return companyRepository.save(company);
    }

    public Company findCompanyById(String id) throws Exception {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isPresent()) {
            return company.get();
        } else {
            throw new Exception("there is no such company with id: " + id);
        }
    }

    public Company findCompanyByName(String name) {
        return companyRepository.findCompanyByName(name);
    }

    public List<Company> findCompaniesByIds(List<String> ids) {
        return mongoTemplate.find(new Query(Criteria.where("id").in(ids)), Company.class, CompanyDao.COLLECTION);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public void removeCompanyById(String id) {
        companyRepository.deleteById(id);
    }
}
