package net.jo.pricetest;

import java.util.List;


public class Price {

    private String p;
    private String q;
    private long T;

    public Price(String p, String q, long t) {
        this.p = p;
        this.q = q;
        T = t;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public long getT() {
        return T;
    }

    public void setT(long t) {
        T = t;
    }
}
