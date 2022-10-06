import Posistion.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public Main() {
    }

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

        while (!isDead) {
            do {
                Thread.sleep(5);
                keyStroke = terminal.pollInput();

            } while (keyStroke == null);

            List<Asteroid> asteroids = new ArrayList<Asteroid>();
            spaceship.movePlayer(terminal, keyStroke);
            spaceship.fire(terminal, keyStroke, asteroids);
            //moveAstroids


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
        } catch (IOException var3) {
            System.out.println(var3.getStackTrace());
            return null;
        }
    }
}
