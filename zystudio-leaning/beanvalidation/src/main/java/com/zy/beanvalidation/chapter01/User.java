package com.zy.beanvalidation.chapter01;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class User implements Serializable {
    @NotNull(message = "{user.id.null}")
    private Long id;

    @NotEmpty(message = "{user.name.null}")
    @Length(min = 5, max = 20, message = "{user.name.length.illegal}")
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "{user.name.illegal}")
    private String name;

    @NotNull(message = "{user.password.null}")
    private String password;


    @NotNull
    @Size(min = 2, max = 5, message = "The email '${validatedValue}' must be between {min} and {max} characters long")
    private String userName;


    @NotNull
    @Email
    /*@MySizeConstraint.List({
            @MySizeConstraint(message = "myasdfasdfsdf1"),
            @MySizeConstraint(message = "myasdfasdfsdf2")
    })*/


    private String email;


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
