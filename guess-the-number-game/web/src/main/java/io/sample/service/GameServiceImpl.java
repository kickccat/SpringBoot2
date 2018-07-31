package io.sample.service;

import io.sample.Game;
import io.sample.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@SessionScope
public class GameServiceImpl implements GameService {
    
    // Fields
    private final Game game;
    private final MessageGenerator messageGenerator;
    
    // Constructor
    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }
    
    @PostConstruct
    public void init() {
        log.info("call the PostConstruct, main message = {}", messageGenerator.getMainMessage());
        log.info("number = {}", game.getNumber());
    }
    
    @Override
    public boolean isGameOver() {
        return game.isGameLost() || game.isGameWon();
    }
    
    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }
    
    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }
    
    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();
    }
    
    @Override
    public void reset() {
        game.reset();
    }
}
