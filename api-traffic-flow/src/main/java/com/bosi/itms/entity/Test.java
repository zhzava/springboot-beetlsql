package com.bosi.itms.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beetl.sql.core.annotatoin.AssignID;

/**
 * Created by zhz on 2018/6/19.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Test对象",description = "测试对象")
public class Test {
    @ApiModelProperty(value = "主键",name = "id",hidden = true)
    private String id;
    @ApiModelProperty(value = "姓名",name = "name",dataType = "String",example = "张三")
    private String name;
    @ApiModelProperty(value = "年龄",name = "age",dataType = "Integer")
    private Integer age;
    @ApiModelProperty(value = "地址",name = "address",dataType = "String",example = "深圳")
    private String address;

    public void setId(String id) {
        this.id = id;
    }
}
