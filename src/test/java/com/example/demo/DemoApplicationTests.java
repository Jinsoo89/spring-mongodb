package com.example.demo;

import com.example.demo.company.Company;
import com.example.demo.company.CompanyRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Member jinsoo = new Member("jinsoo", "developer");
    private Member kim = new Member("kim", "designer");

    @Before
    public void setup() {
        companyRepository.deleteAll();

        Company wiseFashion = new Company(
                "Wisefashion",
                "서울시 중구 마장로 66",
                Arrays.asList(new Team("dev", 5, Arrays.asList(jinsoo))),
                Arrays.asList(jinsoo, kim)
        );

        companyRepository.insert(wiseFashion);
    }


    @Test
    public void testGetAllCompanies() {
        List<Company> companyList = companyRepository.findAll();

        assertNotNull(companyList);
        assertEquals(1, companyList.size());
    }

    @Test
    public void testPostRequest() {
        Company wiserFashion = new Company(
                "Wiserfashion",
                "서울시 중구 마장로 66",
                Arrays.asList(new Team("dev", 5, Arrays.asList(jinsoo))),
                Arrays.asList(jinsoo, kim)
        );

        companyRepository.insert(wiserFashion);
        List<Company> companyList = companyRepository.findAll();

        assertNotNull(companyList);
        assertEquals(2, companyList.size());
        assertEquals("Wiserfashion", companyList.get(1).getName());
    }

    @Test
    public void testPutRequest() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(("Wisefashion")));

        Company wisefashion = mongoTemplate.findOne(query, Company.class);
        wisefashion.setName("Wisestfashion");

        companyRepository.save(wisefashion);
        List<Company> companyList = companyRepository.findAll();

        assertNotNull(companyList);
        assertEquals(companyList.get(0).getName(), "Wisestfashion");
        assertEquals(companyList.get(0).getAddress(), wisefashion.getAddress());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteRequest() {
        String idToDelete = companyRepository.findAll().get(0).getId();

        assertNotNull(idToDelete);

        companyRepository.deleteById(idToDelete);
        // nothing in the collection, should throw the IndexOutOfBoundsException
        companyRepository.findAll().get(0);
    }

    @After
    public void terminate() {
        companyRepository.deleteAll();
    }
}
