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



        int speed = 100;
        while (!isDead) {
            int counter = 0;
            do {
                Thread.sleep(5);
                keyStroke = terminal.pollInput();
                if (counter % 500 == 0) {
                    asteroids.add(new Asteroid());
                }



                if(counter % speed == 0) {
                    for (int i = 0; i < asteroids.size(); i++) {
                        ArrayList<Position> oldPositions = new ArrayList<>();
                        for (int j = 0; j < asteroids.get(i).blockPositions.size(); j++) {
                            oldPositions.add(new Position(asteroids.get(i).blockPositions.get(j).getX(),asteroids.get(i).blockPositions.get(j).getY()));
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
                    }
                    terminal.flush();
                }
                counter++;
            } while (keyStroke == null);


            spaceship.movePlayer(terminal, keyStroke);
            spaceship.fire(terminal, keyStroke, asteroids);


        }

    }

    private static boolean handleGameQuit(boolean continueReadingInput, Character c) throws Exception {
        if (c == Character.valueOf('q')) {
            continueReadingInput = false;
            terminalBuilder().close();
            System.out.println("quit");
        }
        return continueReadingInput;
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
