package com.zy.userservice.domain.readmodel.identity;

import com.zy.userservice.domain.cmdmodel.identity.User;

/**
 * @author by zy.
 */
public class UserDto {
    private String emailAddress;
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String username;

    public UserDto(User aUser) {
        this.initializeFrom(aUser);
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }



    public String getUsername() {
        return this.username;
    }

    private void initializeFrom(User aUser) {
        this.emailAddress = aUser.getUserDetail().getEmailAddress().address();

        this.firstName = aUser.getUserDetail().getFullName().getFirstName();
        this.lastName = aUser.getUserDetail().getFullName().getSecondName();
        this.username = aUser.getName();
    }
}
