package io.sample;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Getter
public class NumberGeneratorImpl implements NumberGenerator {
    
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();
    
    private final int maxNumber;
    
    private final int minNumber;
    
    // == Constructure ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }
    
    @Override
    public int next() {
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }
}