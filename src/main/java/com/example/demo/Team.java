package com.example.demo;

import java.util.List;

public class Team {
    private String name;
    private int floor;
    private List<Member> members;

    public Team(String name, int floor, List<Member> members) {
        this.name = name;
        this.floor = floor;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public int getFloor() {
        return floor;
    }

    public List<Member> getMembers() {
        return members;
    }
}
