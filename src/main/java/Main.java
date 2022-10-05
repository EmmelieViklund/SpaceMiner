import Posistion.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        playGame();
    }

    static void playGame() throws IOException, InterruptedException {
        Terminal terminal = terminalBuilder();

        boolean isDead = false;
        int score = 0;
        KeyStroke keyStroke;

        Spaceship spaceship = new Spaceship(new Position(30, 50));

        while (!isDead) {
            do {
                Thread.sleep(5);
                keyStroke = terminal.pollInput();

            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();

            spaceship.movePlayer(type);

        }
    }

    static Terminal terminalBuilder() {
        try {
            TerminalSize ts = new TerminalSize(60, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setInitialTerminalSize(ts);
            Terminal terminal = terminalFactory.createTerminal();
            return terminal;
        } catch (IOException var3) {
            System.out.println(var3.getStackTrace());
            return null;
        }
    }
}
