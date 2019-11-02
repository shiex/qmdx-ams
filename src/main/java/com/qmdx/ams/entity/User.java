package com.qmdx.ams.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String email;

    private String userName;

    private String password;

    private Role role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        else if(!(obj instanceof User)){
            return false;
        }
        User user = (User) obj;
        return this.toString().equals(user.toString());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (userId == null?0:userId.hashCode());
        result = 31 * result + (userName == null?0:userName.hashCode());
        result = 31 * result + (email == null?0:email.hashCode());
        result = 31 * result + (password == null?0:password.hashCode());
        return result;
        /*return Objects.hash(id,name,state,intro);*/
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", email=" + email +
            ", userName=" + userName +
            ", password=" + password +
        "}";
    }
}
