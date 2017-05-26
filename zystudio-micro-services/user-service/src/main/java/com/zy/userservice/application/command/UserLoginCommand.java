package com.zy.userservice.application.command;

/**
 * @author by zy.
 */
public class UserLoginCommand {
    private String userName;
    private String password;

    public UserLoginCommand(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
}
