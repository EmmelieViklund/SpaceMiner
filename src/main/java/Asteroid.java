import Posistion.Position;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.Random;

public class Asteroid {

    final char block = 9608;
    int size;

    Position asteroidPosition;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Asteroid() {
        Random random = new Random();
        this.asteroidPosition = new Position(random.nextInt(0, 50), 0);

        int i = random.nextInt(0, 10);
        switch (i) {
            case 0, 1, 2 -> {
                size = 2;
            }
            case 3, 4, 5 -> {
                size = 3;
            }
            case 6, 7 -> {
                size = 1;
            }
            case 8, 9 -> {
                size = 5;
            }
        }

    }

    public void moveAsteroid(Terminal terminal, KeyStroke keyStroke) throws Exception {
        int oldX = asteroidPosition.getX(), oldY = asteroidPosition.getY();


        terminal.setCursorPosition(asteroidPosition.getX(), asteroidPosition.getY());
        terminal.putCharacter(block);
        terminal.setCursorPosition(oldX, oldY);
        terminal.putCharacter(' ');
        terminal.flush();

    }

}
