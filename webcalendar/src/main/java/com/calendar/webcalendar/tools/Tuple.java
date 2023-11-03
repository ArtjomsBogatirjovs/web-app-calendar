package com.calendar.webcalendar.tools;

public class Tuple<L, R, T> {
    private L l;
    private R r;

    private T r2;

    public Tuple(L l, R r, T r2) {
        this.l = l;
        this.r = r;
        this.r2 = r2;
    }

    public L getL() {
        return l;
    }

    public R getR() {
        return r;
    }

    public T getR2() {
        return r2;
    }

    public void setL(L l) {
        this.l = l;
    }

    public void setR(R r) {
        this.r = r;
    }

    public void setR2(T r2) {
        this.r2 = r2;
    }
}
