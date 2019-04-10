package com.dikahastanto.antrian.models;

import java.util.List;

public class AntrianResponse {

    private boolean error;
    private List<Antrian> datas_antrian;

    public AntrianResponse(boolean error, List<Antrian> datas_antrian) {
        this.error = error;
        this.datas_antrian = datas_antrian;
    }

    public boolean isError() {
        return error;
    }

    public List<Antrian> getDatas_antrian() {
        return datas_antrian;
    }
}
