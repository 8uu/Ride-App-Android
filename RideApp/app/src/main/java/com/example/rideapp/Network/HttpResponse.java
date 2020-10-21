package com.example.rideapp.Network;

import java.util.List;

public class HttpResponse {
    private List<Item> judete;

    public HttpResponse(List<Item> judete) {
        this.judete = judete;
    }

    public List<Item> getJudete() {
        return judete;
    }

    public void setJudete(List<Item> judete) {
        this.judete = judete;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "judete=" + judete +
                '}';
    }
}
