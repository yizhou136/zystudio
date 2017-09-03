package com.zy.userservice.domain.cmdmodel.identity;

import com.zy.ddd.domain.AbstractDomainObject;
import com.zy.userservice.domain.cmdmodel.identity.events.AuthenticatingUserPassword;
import com.zy.userservice.domain.cmdmodel.identity.events.RegisteringUserDomainEvent;
import com.zy.userservice.domain.readmodel.identity.UserDto;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

/**
 * @author by zy.
 *  @Table 将实体与表映射. 如果没有定义 @Table , 将使用 class name 作为映射表名.
    @Id 声明这个实体的标识属性.
    @Column 将实体的字段与表中的列映射. 如果省略注释 @Column , 将使用字段名作为映射列.
    @OneToOne 定义与另一实体的一对一关系.
    @JoinColumn 声明这个实体是关系的所有者: 这个表带有一个引用表的外键的列.
    @mappedBy 声明是实体关系的被引用者.
 */
@Entity
@Table(name = "t_user")
@EntityListeners({UserEntityListener.class})
public class User extends AbstractDomainObject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   uid;
    @Column(unique = true)
    private String name;
    private String password;
    private String headface;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user", fetch = FetchType.LAZY)
    private UserDetail userDetail;

    private User(){}

    public User(String name, String password, String headface, UserDetail userDetail){
        this.name = name;
        this.password = password;
        this.headface = headface;
        this.userDetail = userDetail;
        this.userDetail.setUser(this);
    }
    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
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
    public String getHeadface() {
        return headface;
    }
    public void setHeadface(String headface) {
        this.headface = headface;
    }
    public UserDetail getUserDetail() {
        return userDetail;
    }
    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    //for business methods
    public UserDto registeUser(){
        Objects.requireNonNull(name, "the user name not be null");
        Objects.requireNonNull(password, "the user password not be null");
        Objects.requireNonNull(headface, "the user password not be null");

        UserDto userDto = new UserDto(this);
        Optional<Boolean> optional =
                publishEvent(RegisteringUserDomainEvent.of(this));
        return userDto;
    }

    public boolean authenticatingUserPassword(String password){
        Objects.requireNonNull(password, "the input password arg not be null");
        Objects.requireNonNull(name, "the user name not be null");
        Objects.requireNonNull(this.password, "the user password not be null");

        /*Optional<Boolean> optional =
                publishEvent(AuthenticatingUserPassword.of(this));*/

        return this.password.contentEquals(password);
    }

    @PrePersist
    private void  prePersist(){
        logger.info("prePersist xxxxxxx");
    }

    @PostPersist
    private void postPersist(){
        logger.info("postPersist xxxxxxx");
    }

    @PostRemove
    private void postRemove(){
        logger.info("postRemove xxxxxxx");
    }

    @PreRemove
    private void preRemove(){
        logger.info("preRemove xxxxxxx");
    }

    @PostUpdate
    private void postUpdate(){
        logger.info("postUpdate xxxxxxx");
    }

    @PostLoad
    private void postLoad(){
        logger.info("postLoad xxxxxxx");
    }

    @PreUpdate
    private void preUpdate(){
        logger.info("preUpdate xxxxxxx");
    }
}