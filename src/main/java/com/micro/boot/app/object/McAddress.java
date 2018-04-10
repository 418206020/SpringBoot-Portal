package com.micro.boot.app.object;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈McAddress〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class McAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;

    //分类
    private String type;
    //卫星坐标
    private String coordinate;
    //是否自定义：0自定义，1划分
    private Integer isDefined;
    //自定义地址
    private String defAddress;
    //国
    private String undefNation;
    //省
    private String undefProvince;
    //市
    private String undefCity;
    //县
    private String undefCounty;
    //乡
    private String undefTown;
    //村
    private String undefVillage;
    //户
    private String undefHouse;
    //
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getIsDefined() {
        return isDefined;
    }

    public void setIsDefined(Integer isDefined) {
        this.isDefined = isDefined;
    }

    public String getDefAddress() {
        return defAddress;
    }

    public void setDefAddress(String defAddress) {
        this.defAddress = defAddress;
    }

    public String getUndefNation() {
        return undefNation;
    }

    public void setUndefNation(String undefNation) {
        this.undefNation = undefNation;
    }

    public String getUndefProvince() {
        return undefProvince;
    }

    public void setUndefProvince(String undefProvince) {
        this.undefProvince = undefProvince;
    }

    public String getUndefCity() {
        return undefCity;
    }

    public void setUndefCity(String undefCity) {
        this.undefCity = undefCity;
    }

    public String getUndefCounty() {
        return undefCounty;
    }

    public void setUndefCounty(String undefCounty) {
        this.undefCounty = undefCounty;
    }

    public String getUndefTown() {
        return undefTown;
    }

    public void setUndefTown(String undefTown) {
        this.undefTown = undefTown;
    }

    public String getUndefVillage() {
        return undefVillage;
    }

    public void setUndefVillage(String undefVillage) {
        this.undefVillage = undefVillage;
    }

    public String getUndefHouse() {
        return undefHouse;
    }

    public void setUndefHouse(String undefHouse) {
        this.undefHouse = undefHouse;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}