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
public class McRegion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fid;
    private String regionCode;
    private String regionName;
    private String regionNameEn;
    private String regionShortnameEn;
    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionNameEn() {
        return regionNameEn;
    }

    public void setRegionNameEn(String regionNameEn) {
        this.regionNameEn = regionNameEn;
    }

    public String getRegionShortnameEn() {
        return regionShortnameEn;
    }

    public void setRegionShortnameEn(String regionShortnameEn) {
        this.regionShortnameEn = regionShortnameEn;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}