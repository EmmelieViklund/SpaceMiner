import Posistion.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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
        terminal.setCursorPosition(spaceship.playerPosition.getX(), spaceship.playerPosition.getY());
        terminal.putCharacter(spaceship.playerChar);
        terminal.flush();


        List<Asteroid> asteroids = new ArrayList<>();
//        for (int i = 0; i < asteroids.size(); i++) {
//            Asteroid asteroid = new Asteroid();
//            asteroids.add(asteroid);
//            terminal.setCursorPosition(asteroid.blockPositions.get(i).getX(), asteroid.blockPositions.get(i).getY());
//            terminal.putCharacter((asteroid.block));
//        }


        int speed = 200;
        int spawnSpeed = 500;
        while (!isDead) {
            int counter = 0;
            do {
                Thread.sleep(5);
                keyStroke = terminal.pollInput();
                if (counter % spawnSpeed == 0) {
                    asteroids.add(new Asteroid());
                }
                if (counter % speed == 0) {
                    for (int i = 0; i < asteroids.size(); i++) {
                        ArrayList<Position> oldPositions = new ArrayList<>();
                        for (int j = 0; j < asteroids.get(i).blockPositions.size(); j++) {
                            oldPositions.add(new Position(asteroids.get(i).blockPositions.get(j).getX(), asteroids.get(i).blockPositions.get(j).getY()));
                        }
                        asteroids.get(i).moveAsteroid();
                        for (int j = 0; j < oldPositions.size(); j++) {
                            terminal.setCursorPosition(oldPositions.get(j).getX(), oldPositions.get(j).getY());
                            terminal.putCharacter(' ');
                        }
                        for (int j = 0; j < asteroids.get(i).blockPositions.size(); j++) {
                            terminal.setCursorPosition(asteroids.get(i).blockPositions.get(j).getX(), asteroids.get(i).blockPositions.get(j).getY());
                            terminal.putCharacter(asteroids.get(i).block);
                        }
                        if (asteroids.get(i).blockPositions.get(0).getY() > 38) {
                            for (int j = 0; j < asteroids.get(i).blockPositions.size(); j++) {
                                terminal.setCursorPosition(asteroids.get(i).blockPositions.get(j).getX(), asteroids.get(i).blockPositions.get(j).getY());
                                terminal.putCharacter(' ');
                            }
                            asteroids.remove(asteroids.get(i));
                        }
                        isDead = deadCheck(asteroids, spaceship.playerPosition);
                    }
                    terminal.flush();
                }
                counter++;
            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();

            if (type == KeyType.ArrowLeft || type == KeyType.ArrowRight) {
                spaceship.movePlayer(terminal, type);
            } else if (type == KeyType.Character && keyStroke.getCharacter() == ' ') {
                score += spaceship.fire(terminal, asteroids);
            } else if (type == KeyType.Character && keyStroke.getCharacter() == 'q') {
                handleGameQuit(terminal, keyStroke.getCharacter());
            }

            String scoreString = String.format("Score: %04d", score);
            for (int i = 0; i < scoreString.length(); i++) {
                terminal.setCursorPosition((60 - scoreString.length()) + i, 0);
                terminal.putCharacter(scoreString.charAt(i));
            }
            if (score % 10 == 0) {
                speed -=5;
                if (speed < 1) {
                    speed = 1;
                }
                spawnSpeed -= 10;
                if (spawnSpeed < 1) {
                    spawnSpeed = 1;
                }
            }
        }
        terminal.close();
    }

    private static void handleGameQuit(Terminal terminal, Character c) throws Exception {
        if (c == Character.valueOf('q')) {
            terminal.close();
            System.out.println("quit");
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
    static boolean deadCheck(List<Asteroid> asteroids, Position p) {
        for (Asteroid a : asteroids) {
            for (int i = 0; i < a.blockPositions.size(); i++) {
                if (a.blockPositions.get(i).equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }
}
