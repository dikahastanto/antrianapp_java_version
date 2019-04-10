package com.dikahastanto.antrian.models;

public class Antrian {

    private String no_panggil,username,nama_lengkap,jam,status,created_at;

    public Antrian(String no_panggil, String username, String nama_lengkap,String jam,String status,String created_at) {
        this.no_panggil = no_panggil;
        this.username = username;
        this.nama_lengkap = nama_lengkap;
        this.jam = jam;
        this.status = status;
        this.created_at = created_at;
    }

    public String getNo_panggil() {
        return no_panggil;
    }

    public String getUsername() {
        return username;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public String getJam() {
        return jam;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }
}
