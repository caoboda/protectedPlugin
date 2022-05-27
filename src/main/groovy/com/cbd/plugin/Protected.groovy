package com.cbd.plugin;

class Protected {
    //加固用户名
    String userName
    //加固密码
    String password
    //加固工具
    String protectedTools

    String getUserName() {
        return userName
    }

    void setUserName(String userName) {
        this.userName = userName
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    String getProtectTools() {
        return protectedTools
    }

    void setProtectTools(String protectedTools) {
        this.protectedTools = protectedTools
    }
}
