package com.mys.dto;

import java.util.Date;

public class Topic {
    /**
     * 修改状态系统 传入的tid
     * System.currentTimeMillis()
     */
    private long tid;

    /**
     * ECLP订单编号
     */
    private String soNo;

    /**
     * 事业部Id
     */
    private Long deptId;

    /**
     * 店铺Id
     */
    private Long shopId;
    /**
     * 状态编码
     */
    private int statusCode;
    /**
     * 修改前状态编码
     */
    private String statusCodeBefore;
    /**
     * 修改状态的系统
     */
    private String operator;
    /**
     * 修改完成时的时间
     */
    private Date updateTime;

}
