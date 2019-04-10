package com.dikahastanto.antrian.models;

public class User {

    private String username,email,nama_lengkap;

    public User(String username, String email, String nama_lengkap) {
        this.username = username;
        this.email = email;
        this.nama_lengkap = nama_lengkap;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }
}
