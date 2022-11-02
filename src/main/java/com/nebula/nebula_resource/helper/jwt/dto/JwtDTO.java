package com.nebula.nebula_resource.helper.jwt.dto;

import java.util.List;

public class JwtDTO {
    private int id;
    private String username;
    private List<String> roles;

    public JwtDTO() {
    }

    public JwtDTO(int id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "JwtDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + roles +
                '}';
    }
}
