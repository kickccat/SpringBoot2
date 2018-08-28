package io.sample.springbootapi.util;

import io.sample.springbootapi.domain.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class BookUtil {
    
    public static void convertBook(Book book, Book todoBook) {
        String[] ignorePropertyNames = getIgnorePropertyNames(book);
        BeanUtils.copyProperties(book, todoBook, ignorePropertyNames);
    }
    
    private static String[] getIgnorePropertyNames(Object source) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] properties = beanWrapper.getPropertyDescriptors();
        
        List<String> ignorePropertyNames = new ArrayList<>();
        
        for (PropertyDescriptor pd : properties) {
            String propertyName = pd.getName();
            Object value = beanWrapper.getPropertyValue(propertyName);
            if (value == null) {
                ignorePropertyNames.add(propertyName);
            }
        }
        return ignorePropertyNames.toArray(new String[0]);
    }
}
