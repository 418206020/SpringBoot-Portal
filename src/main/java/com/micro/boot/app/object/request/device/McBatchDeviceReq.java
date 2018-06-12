package com.micro.boot.app.object.request.device;

import com.micro.boot.app.object.McAddress;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈MC设备 （此表只记录公共参数）〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class McBatchDeviceReq implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分页SQL固定
     * offset
     * limit
     */
    private Integer offset;
    private Integer limit;
    //告警状态
    private Integer devStatus;
    //排序
    private String orderString;
    //设备类别
    private String devType;
    //MC用户
    private Long userId;
    //详细地址
    private Long addressId;//关联
    private McAddress mcAddress;
    //设备模式
    private Integer devMode;
    //创建时间
    private Date createTime;


    /**
     * 公共方法
     */
    public void setPage(Integer pageNo, Integer pageSize) {
        this.setOffset(pageSize * (pageNo - 1));
        this.setLimit(pageSize * pageNo);
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
    }

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public McAddress getMcAddress() {
        return mcAddress;
    }

    public void setMcAddress(McAddress mcAddress) {
        this.mcAddress = mcAddress;
    }

    public Integer getDevMode() {
        return devMode;
    }

    public void setDevMode(Integer devMode) {
        this.devMode = devMode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}