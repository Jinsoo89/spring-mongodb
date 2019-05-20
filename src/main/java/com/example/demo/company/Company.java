package com.example.demo.company;

import com.example.demo.Member;
import com.example.demo.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = CompanyDao.COLLECTION)
public class Company {
    @Id
    private String id;
    private String name;
    private String address;
    private List<Team> teams;
    private List<Member> members;

    public Company(String name, String address, List<Team> teams, List<Member> members) {
        this.name = name;
        this.address = address;
        this.teams = teams;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Member> getMembers() {
        return members;
    }
}
