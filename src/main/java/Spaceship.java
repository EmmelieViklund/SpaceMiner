import Posistion.Position;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class Spaceship {
    final char playerChar = 'O';
    final char verticalLine = '\u007C';

    Position playerPosition;

    public Spaceship(Position playerPosition) {
        this.playerPosition = playerPosition;
    }

    public void movePlayer(Terminal terminal, KeyStroke keyStroke) throws Exception {

        switch (keyStroke.getKeyType()) {

            case ArrowLeft -> {
                if (playerPosition.getX() > 0) {
                    this.playerPosition.setX(playerPosition.getX() - 1);
                }
            }

            case ArrowRight -> {
                if (playerPosition.getX() < 57) {
                    this.playerPosition.setX(playerPosition.getX() + 1);
                }
            }
        }
        terminal.setCursorPosition(playerPosition.getX(), playerPosition.getY());
        terminal.putCharacter(playerChar);
        terminal.flush();

    }


    public void fire(Terminal terminal, KeyStroke keyStroke, List<Asteroids> asteroids) throws Exception {
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == ' ') {
            int y = playerPosition.getY()-1;
            while (true) {
                y--;
                if (y < 0) {
                    break;
                }
                terminal.setCursorPosition(playerPosition.getX(), y);
                terminal.putCharacter(verticalLine);
                terminal.flush();
            }
            Thread.sleep(50);
            for (int i = y; i < playerPosition.getY(); i++) {
                terminal.setCursorPosition(playerPosition.getX(), i);
                terminal.putCharacter(' ');
                terminal.flush();
            }
        }
    }
}

