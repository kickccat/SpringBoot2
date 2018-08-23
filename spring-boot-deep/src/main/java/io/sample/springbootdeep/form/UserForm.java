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
    
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    @Length(min = 6, max = 20, message = "密码至少需要6位")
    private String password;
    
    @NotBlank(message = "密码错误")
    private String confirmPassword;
    
    @Pattern(regexp = PHONE_REG, message = "请输入正确的手机号")
    private String phone;
    
    @NotBlank(message = "请填写邮箱")
    @Email(message = "邮箱格式不正确")
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
    
    public boolean identityPassword() {
        return this.password.equals(this.confirmPassword);
    }
}