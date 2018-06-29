package com.bosi.itms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhz on 2018/6/25.
 */
@ApiModel(value = "分页对象")
public class PageParam {
    @ApiModelProperty(value = "页码",dataType = "long",example = "1")
    private long pageNumber = 1;
    @ApiModelProperty(value = "每页条数",dataType = "long",example = "20")
    private long pageSize = 20;
    @ApiModelProperty(value = "排序规则",example = "create_time desc",dataType = "String")
    private String orderBy;

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
