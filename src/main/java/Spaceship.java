import Posistion.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.security.Key;

public class Spaceship {
    final char playerChar = '\u0020';

    Position playerPosition;

    public Spaceship(Position playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Position movePlayer (KeyType direction) {

   switch (direction) {

       case ArrowLeft-> {
            this.playerPosition.setX(playerPosition.getX() - 1);
            return this.playerPosition;
        }

        case ArrowRight -> {
            this.playerPosition.setX(playerPosition.getX() + 1);
            return this.playerPosition;
        }

    }
}


}

