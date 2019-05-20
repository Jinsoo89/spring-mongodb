package com.example.demo.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyDao companyDao;

    public void addCompanyAsBody(Company company) {
        companyDao.insertCompanyAsBody(company);
    }

    public Company updateCompanyAsBody(Company company) {
        return companyDao.updateCompanyAsBody(company);
    }

    public Company getCompanyById(String id) throws Exception {
        return companyDao.findCompanyById(id);
    }

    public List<Company> getAllCompanies() {
        return companyDao.findAllCompanies();
    }

    public void removeCompanyById(String id) {
        companyDao.removeCompanyById(id);
    }
}
