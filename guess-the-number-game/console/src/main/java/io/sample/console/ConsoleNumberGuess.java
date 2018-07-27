package io.sample.console;

import io.sample.Game;
import io.sample.MessageGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@AllArgsConstructor
public class ConsoleNumberGuess {
    
    // == fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;
    
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start() --> Container ready for use.");
    
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());
    
            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();
    
            if (game.isGameWon() || game.isGameLost()) {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");
    
                String playAgainString = scanner.nextLine().trim();
                if (!playAgainString.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }
        }
    }
}
