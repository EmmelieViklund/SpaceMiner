import Posistion.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        playGame();
    }

    static void playGame() throws Exception {
        Terminal terminal = terminalBuilder();

        boolean isDead = false;
        int score = 0;
        KeyStroke keyStroke;

        Spaceship spaceship = new Spaceship(new Position(30, 37));
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Asteroid asteroid = new Asteroid();
            asteroids.add(asteroid);
            terminal.setCursorPosition(asteroid.blockPositions.get(i).getX(), asteroid.blockPositions.get(i).getY());
            terminal.putCharacter((asteroid.block));
        }

        terminal.setCursorPosition(spaceship.playerPosition.getX(), spaceship.playerPosition.getY());
        terminal.putCharacter(spaceship.playerChar);
        terminal.flush();

        int speed = 100;
        while (!isDead) {
            int counter = 0;
            do {
                Thread.sleep(5);
                keyStroke = terminal.pollInput();
                if(counter % speed == 0) {
                    for (Asteroid a : asteroids) {
                        ArrayList<Position> oldPositions = new ArrayList<>(a.blockPositions);
                        a.moveAsteroid();
                        for (int i = 0; i < oldPositions.size(); i++) {
                            terminal.setCursorPosition(oldPositions.get(i).getX(), oldPositions.get(i).getY());
                            terminal.putCharacter(' ');
                        }
                        for (int i = 0; i < a.blockPositions.size(); i++) {
                            terminal.setCursorPosition(a.blockPositions.get(i).getX(), a.blockPositions.get(i).getY());
                            terminal.putCharacter(a.block);
                        }
                    }
                }
                counter++;
            } while (keyStroke == null);


            spaceship.movePlayer(terminal, keyStroke);
            spaceship.fire(terminal, keyStroke, asteroids);
        }
    }

    static Terminal terminalBuilder() {
        try {
            TerminalSize ts = new TerminalSize(60, 38);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setInitialTerminalSize(ts);
            Terminal terminal = terminalFactory.createTerminal();
            terminal.setCursorVisible(false);
            return terminal;
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
