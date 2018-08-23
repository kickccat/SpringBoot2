package io.sample.springbootdeep.form;

public interface FormConvert<S, T> {
    
    T convertTo(S s);
}
