package com.sbrt.ponomarev.githubuser;

/**
 * Created by Ponomarev on 24.05.2017.
 */

class User {
    private String name;
    private String email;
    private Integer id;
    private String location;

    public User() {
    }

    public User(String name, String email, Integer id, String location) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getID() {
        return id;
    }

    public String getLocation() {
        return location;
    }
}
