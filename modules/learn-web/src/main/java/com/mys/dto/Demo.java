package com.mys.dto;


import org.apache.commons.lang3.builder.ToStringBuilder;

public class Demo extends DemoParent{

    private int a;
    private Integer b;
    private Long c;
    private long d;
    private String f;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Long getC() {
        return c;
    }

    public void setC(Long c) {
        this.c = c;
    }

    public long getD() {
        return d;
    }

    public void setD(long d) {
        this.d = d;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("a", a)
                .append("b", b)
                .append("c", c)
                .append("d", d)
                .append("f", f)
                .toString();
    }
}
