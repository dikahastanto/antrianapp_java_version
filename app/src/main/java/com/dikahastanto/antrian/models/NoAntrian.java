package com.dikahastanto.antrian.models;

public class NoAntrian {

    private String id,no_panggil,jam;

    public NoAntrian(String id,String no_panggil, String jam) {
        this.id = id;
        this.no_panggil = no_panggil;
        this.jam = jam;
    }

    public String getId() {
        return id;
    }

    public String getNo_panggil() {
        return no_panggil;
    }

    public String getJam() {
        return jam;
    }
}
