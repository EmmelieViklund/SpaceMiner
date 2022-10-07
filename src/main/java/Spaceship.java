import Posistion.Position;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;

public class Spaceship {
    final char playerChar = '\u261D';
    final char verticalLine = '\u007C';


    Position playerPosition;

    public Spaceship(Position playerPosition) {
        this.playerPosition = playerPosition;
    }

    public void movePlayer(Terminal terminal, KeyType type) throws Exception {
        int oldX = playerPosition.getX(), oldY = playerPosition.getY();

        switch (type) {

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
        terminal.setCursorPosition(oldX, oldY);
        terminal.putCharacter(' ');
        terminal.flush();

    }


    public int fire(Terminal terminal, List<Asteroid> asteroids) throws Exception {
        int y = playerPosition.getY();
        Position hitPosition = null;
        while (true) {
            y--;
            if (y < 0) {
                break;
            }
            hitPosition = hitCoordinates(asteroids, this.playerPosition, y);
            if (hitPosition != null) {
                for (int i = 0; i < asteroids.size(); i++) {
                    for (int j = 0; j < asteroids.get(i).blockPositions.size(); j++) {
                        if(hitPosition.equals(asteroids.get(i).blockPositions.get(j))) {
                            asteroids.get(i).blockPositions.remove(j);
                            if(asteroids.get(i).blockPositions.isEmpty())
                                asteroids.remove(i);
                            break;
                        }
                    }
                }
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
        if (hitPosition != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public Position hitCoordinates(List<Asteroid> asteroids, Position playerPosition, int y) {
       Position laserPosition = new Position(playerPosition.getX(),y);
        for (Asteroid a:asteroids) {
            for (Position p: a.blockPositions) {
                if (laserPosition.equals(p)) {
                    return laserPosition;
                }
            }
        }
        return null;
    }
//    public void removeBlock(List<Asteroid> asteroids, Position playerPosition, int y) {
//        Position laserPosition = new Position(playerPosition.getX(), y);
//        for (Asteroid asteroid : asteroids) {
//            for (int j = 0; j < asteroid.blockPositions.size(); j++) {
//                if (laserPosition.equals(asteroid.blockPositions.get(j))) {
//                    asteroid.blockPositions.remove(j);
//
//                }
//            }
//        }
//    }
}


