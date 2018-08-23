package io.sample.springbootdeep.validation;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    
    String message() default "Invalid Password";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
