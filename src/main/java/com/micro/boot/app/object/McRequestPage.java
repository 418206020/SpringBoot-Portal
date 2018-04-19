package com.micro.boot.app.object;

import java.io.Serializable;

/**
 * 〈MC 分页请求公共类〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class McRequestPage implements Serializable {
    private static final long serialVersionUID = 1L;

    //当前页
    private Integer pageNo = 0;
    //每页大小
    private Integer pageSize = 10;//默认分页数

    //排序字段-多字段排序：举例=createTime,size
    private String orderBy;
    //每个字段的排序方式【顺序Y，倒叙N】
    private String orderDesc;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

}