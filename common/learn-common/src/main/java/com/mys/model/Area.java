package com.mys.model;

import java.util.Date;

/**
 * Created by mengyusheng on 2017/5/3.
 */
public class Area {
    private int id;
    private int areaId;
    private String areaName;
    private int State;

    private Date createDate;
    private String createPin;
    private String updatePin;
    private Date updateDate;
    private boolean yn; //是否有效 1 有效 0 无效

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatePin() {
        return createPin;
    }

    public void setCreatePin(String createPin) {
        this.createPin = createPin;
    }

    public String getUpdatePin() {
        return updatePin;
    }

    public void setUpdatePin(String updatePin) {
        this.updatePin = updatePin;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isYn() {
        return yn;
    }

    public void setYn(boolean yn) {
        this.yn = yn;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Area{");
        sb.append("id=").append(id);
        sb.append(", areaId=").append(areaId);
        sb.append(", areaName='").append(areaName).append('\'');
        sb.append(", State=").append(State);
        sb.append(", createDate=").append(createDate);
        sb.append(", createPin='").append(createPin).append('\'');
        sb.append(", updatePin='").append(updatePin).append('\'');
        sb.append(", updateDate=").append(updateDate);
        sb.append(", yn=").append(yn);
        sb.append('}');
        return sb.toString();
    }
}
