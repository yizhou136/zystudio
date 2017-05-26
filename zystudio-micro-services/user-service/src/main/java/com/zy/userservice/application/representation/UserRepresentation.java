package com.zy.userservice.application.representation;


import com.zy.userservice.domain.cmdmodel.identity.User;

/**
 * @author by zy.
 */
public class UserRepresentation {

    private String emailAddress;
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String username;

    public UserRepresentation(User aUser) {
        super();

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

    protected UserRepresentation() {
        super();
    }

    private void initializeFrom(User aUser) {
        this.emailAddress = aUser.getUserDetail().getEmailAddress().address();

        this.firstName = aUser.getUserDetail().getFullName().getFirstName();
        this.lastName = aUser.getUserDetail().getFullName().getSecondName();
        this.username = aUser.getName();
    }
}
