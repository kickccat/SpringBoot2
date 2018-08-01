package io.sample.config;

import io.sample.Game;
import io.sample.GameImpl;
import io.sample.GuessCount;
import io.sample.NumberGenerator;
import io.sample.interceptor.RequestInterceptor;
import io.sample.util.ViewNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    // Bean methods
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(ViewNames.HOME);
    }
    
    @Bean
    @SessionScope
    public Game game(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        return new GameImpl(numberGenerator, guessCount);
    }
}
