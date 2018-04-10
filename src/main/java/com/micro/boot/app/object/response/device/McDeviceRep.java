package com.micro.boot.app.object.response.device;

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
    //设备英文名称
    private String dev_name_en;
    //设备中文名称
    private String dev_name_zh;
    //MC用户
    private Long user_id;
    //设备类别
    private String dev_type;
    //设备详细参数信息
    private String dev_info_id;
    //设备状态
    private Integer dev_status;
    //设备模式
    private Integer dev_mode;
    //电量%
    private String electricity;
    //WIFI状态
    private Integer status_wifi;
    //蓝牙状态
    private Integer status_bluetooth;
    //声音状态
    private Integer status_voice;
    //开关状态
    private Integer status_switch;
    //公共扩展参数1
    private String public_extend_param1;
    //公共扩展参数2
    private String public_extend_param2;
    //公共扩展参数3
    private String public_extend_param3;
    //创建时间
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDev_name_en() {
        return dev_name_en;
    }

    public void setDev_name_en(String dev_name_en) {
        this.dev_name_en = dev_name_en;
    }

    public String getDev_name_zh() {
        return dev_name_zh;
    }

    public void setDev_name_zh(String dev_name_zh) {
        this.dev_name_zh = dev_name_zh;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDev_type() {
        return dev_type;
    }

    public void setDev_type(String dev_type) {
        this.dev_type = dev_type;
    }

    public String getDev_info_id() {
        return dev_info_id;
    }

    public void setDev_info_id(String dev_info_id) {
        this.dev_info_id = dev_info_id;
    }

    public Integer getDev_status() {
        return dev_status;
    }

    public void setDev_status(Integer dev_status) {
        this.dev_status = dev_status;
    }

    public Integer getDev_mode() {
        return dev_mode;
    }

    public void setDev_mode(Integer dev_mode) {
        this.dev_mode = dev_mode;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public Integer getStatus_wifi() {
        return status_wifi;
    }

    public void setStatus_wifi(Integer status_wifi) {
        this.status_wifi = status_wifi;
    }

    public Integer getStatus_bluetooth() {
        return status_bluetooth;
    }

    public void setStatus_bluetooth(Integer status_bluetooth) {
        this.status_bluetooth = status_bluetooth;
    }

    public Integer getStatus_voice() {
        return status_voice;
    }

    public void setStatus_voice(Integer status_voice) {
        this.status_voice = status_voice;
    }

    public Integer getStatus_switch() {
        return status_switch;
    }

    public void setStatus_switch(Integer status_switch) {
        this.status_switch = status_switch;
    }

    public String getPublic_extend_param1() {
        return public_extend_param1;
    }

    public void setPublic_extend_param1(String public_extend_param1) {
        this.public_extend_param1 = public_extend_param1;
    }

    public String getPublic_extend_param2() {
        return public_extend_param2;
    }

    public void setPublic_extend_param2(String public_extend_param2) {
        this.public_extend_param2 = public_extend_param2;
    }

    public String getPublic_extend_param3() {
        return public_extend_param3;
    }

    public void setPublic_extend_param3(String public_extend_param3) {
        this.public_extend_param3 = public_extend_param3;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}