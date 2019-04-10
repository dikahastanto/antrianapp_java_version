package com.dikahastanto.antrian.models;

public class NoAntrianResponse {

    private boolean error;
    private NoAntrian data_antrian;

    //generate constructor

    public NoAntrianResponse(boolean error, NoAntrian noAntrian) {
        this.error = error;
        this.data_antrian = noAntrian;
    }

    //generate getter
    public boolean isError() {
        return error;
    }

    public NoAntrian getData_antrian() {
        return data_antrian;
    }
}
