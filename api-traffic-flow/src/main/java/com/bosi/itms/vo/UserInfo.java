package com.bosi.itms.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhz on 2018/6/28.
 */
public class UserInfo {

    private long userId;
    @ApiModelProperty(value = "昵称",name = "name",dataType = "String",example = "张三")
    private String name;
    @ApiModelProperty(value = "密码",name = "name",dataType = "String",example = "123456")
    private String password;
    private String salt;
    private Integer state;
    @ApiModelProperty(value = "用户名",name = "name",dataType = "String",example = "admin")
    private String username;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}
