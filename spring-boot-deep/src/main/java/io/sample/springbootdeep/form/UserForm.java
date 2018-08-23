package io.sample.springbootdeep.form;

import io.sample.springbootdeep.domain.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserForm {
    
    public static final String PHONE_REG = "^((13[0-9])|(15[^4])|(18[0235-9])|(17[0-8])|(147))\\d{8}$";
    
    @NotBlank
    private String username;
    
    @NotBlank
    @Length(min = 6, message = "密码至少需要6位")
    private String password;
    
    @NotBlank
    private String confirmPassword;
    
    @Pattern(regexp = PHONE_REG, message = "请输入正确的手机号")
    private String phone;
    
    @Email
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