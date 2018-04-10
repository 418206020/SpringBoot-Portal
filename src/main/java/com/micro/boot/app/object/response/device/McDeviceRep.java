package com.micro.boot.app.object.response.device;

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
public class McDeviceRep implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //设备类别
    private String devType;
    //devMacid
    private String devMacid;
    //设备英文名称
    private String devNameEn;
    //设备中文名称
    private String devNameZh;
    //MC用户
    private Long userId;
    //addressId
//    private Long addressId;
    private McAddress mcAddress;
    //设备详细参数信息
    private String devInfoId;
    //设备状态
    private Integer devStatus;
    //设备模式
    private Integer devMode;
    //电量%
    private String electricity;
    //WIFI状态
    private Integer statusWifi;
    //蓝牙状态
    private Integer statusBluetooth;
    //声音状态
    private Integer statusVoice;
    //开关状态
    private Integer statusSwitch;
    //公共扩展参数1
    private String publicExtendParam1;
    //公共扩展参数2
    private String publicExtendParam2;
    //公共扩展参数3
    private String publicExtendParam3;
    //创建时间
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getDevMacid() {
        return devMacid;
    }

    public void setDevMacid(String devMacid) {
        this.devMacid = devMacid;
    }

    public String getDevNameEn() {
        return devNameEn;
    }

    public void setDevNameEn(String devNameEn) {
        this.devNameEn = devNameEn;
    }

    public String getDevNameZh() {
        return devNameZh;
    }

    public void setDevNameZh(String devNameZh) {
        this.devNameZh = devNameZh;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public McAddress getMcAddress() {
        return mcAddress;
    }

    public void setMcAddress(McAddress mcAddress) {
        this.mcAddress = mcAddress;
    }

    public String getDevInfoId() {
        return devInfoId;
    }

    public void setDevInfoId(String devInfoId) {
        this.devInfoId = devInfoId;
    }

    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
    }

    public Integer getDevMode() {
        return devMode;
    }

    public void setDevMode(Integer devMode) {
        this.devMode = devMode;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public Integer getStatusWifi() {
        return statusWifi;
    }

    public void setStatusWifi(Integer statusWifi) {
        this.statusWifi = statusWifi;
    }

    public Integer getStatusBluetooth() {
        return statusBluetooth;
    }

    public void setStatusBluetooth(Integer statusBluetooth) {
        this.statusBluetooth = statusBluetooth;
    }

    public Integer getStatusVoice() {
        return statusVoice;
    }

    public void setStatusVoice(Integer statusVoice) {
        this.statusVoice = statusVoice;
    }

    public Integer getStatusSwitch() {
        return statusSwitch;
    }

    public void setStatusSwitch(Integer statusSwitch) {
        this.statusSwitch = statusSwitch;
    }

    public String getPublicExtendParam1() {
        return publicExtendParam1;
    }

    public void setPublicExtendParam1(String publicExtendParam1) {
        this.publicExtendParam1 = publicExtendParam1;
    }

    public String getPublicExtendParam2() {
        return publicExtendParam2;
    }

    public void setPublicExtendParam2(String publicExtendParam2) {
        this.publicExtendParam2 = publicExtendParam2;
    }

    public String getPublicExtendParam3() {
        return publicExtendParam3;
    }

    public void setPublicExtendParam3(String publicExtendParam3) {
        this.publicExtendParam3 = publicExtendParam3;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}