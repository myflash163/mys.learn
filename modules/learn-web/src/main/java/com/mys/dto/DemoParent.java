package com.mys.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DemoParent {
    private int aa;
    private Integer bb;
    private Long cc;
    private long dd;
    private String ff;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("aa", aa)
                .append("bb", bb)
                .append("cc", cc)
                .append("dd", dd)
                .append("ff", ff)
                .toString();
    }
}
