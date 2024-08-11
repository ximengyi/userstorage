/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.angelalign.userstorage.entity;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */


public class UserEntity {


    private int accountId;
    private int userId;
    private String username;
    private String password;
    private int strength;
    private int isDelete;
    private String createBy;
    private String createTime;
    private String modifyBy;
    private String modifyTime;
    private String pwdTime;
    private String site;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }



    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPwdTime() {
        return pwdTime;
    }

    public void setPwdTime(String pwdTime) {
        this.pwdTime = pwdTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", strength=" + strength +
                ", isDelete=" + isDelete +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyBy='" + modifyBy + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", site='" + site + '\'' +
                ", pwdTime='" + pwdTime + '\'' +
                '}';
    }
}
