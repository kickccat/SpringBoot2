package io.sample.springbootdeep.form;

import io.sample.springbootdeep.domain.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserForm {
    
    private String username;
    private String password;
    private String confirmPassword;
    private int phone;
    private String email;
    
    public UserForm() {
    }
    
    public User convertToUser() {
        return new UserFormConvert().convertTo(this);
    }
    
    private class UserFormConvert implements FormConvert<UserForm, User> {
        @Override
        public User convertTo(UserForm userForm) {
            User user = new User();
            BeanUtils.copyProperties(userForm, user);
            return user;
        }
    }
}