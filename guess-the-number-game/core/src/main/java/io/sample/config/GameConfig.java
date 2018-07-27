package io.sample.config;

import io.sample.GuessCount;
import io.sample.MaxNumber;
import io.sample.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "io.sample")
public class GameConfig {
    
    // == fields ==
    @Value("${game.maxNumber:20}")
    private int maxNumber;
    
    @Value("${game.minNumber:5}")
    private int minNumber;
    
    @Value("${game.guessCount:5}")
    private int guessCount;
    
    // Bean methods
    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }
    
    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }
    
    @Bean
    @MinNumber
    public int minNumber() {
        return minNumber;
    }
}
