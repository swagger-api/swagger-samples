package io.swagger.sample.models;

public class User {
    private Long id;
    private String user;

    public User id(Long id) {
        this.id = id;
        return this;
    }

    public User user(String user) {
        this.user = user;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}