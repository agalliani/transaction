package com.transaction.transaction.proposal;

public class Proposal {
    Integer id;
    String name;
    String description;

    // constructors
    public Proposal() {
    }

    public Proposal(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

