package com.zy.userservice.domain.cmdmodel.identity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author by zy.
 */
@Entity
@Table(name = "t_user_detail")
public class UserDetail implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Embedded
    private FullName fullName;
    @Embedded
    private EmailAddress emailAddress;
    @Embedded
    private Telephone telephone;
    private Integer age;

    public UserDetail() {
    }

    public UserDetail(FullName fullName, EmailAddress emailAddress, Telephone telephone, Integer age) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.telephone = telephone;
        this.age = age;
    }

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public FullName getFullName() {
        return fullName;
    }
    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }
    public EmailAddress getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }
    public Telephone getTelephone() {
        return telephone;
    }
    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }
}
