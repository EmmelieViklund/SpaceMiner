import Posistion.Position;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyType;


import java.util.ArrayList;
import java.util.Random;

public class Asteroid {

    final char block = 9608;
    int size;
    ArrayList<Position> blockPositions;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Asteroid() {
        Random random = new Random();
        this.blockPositions = new ArrayList<>();

        int r1 = random.nextInt(0, 10);
        switch (r1) {
            case 0 -> {
                size = 1;
            }
            case 1, 2, 3 -> {
                size = 2;
            }
            case 4, 5, 6 -> {
                size = 3;
            }
            case 7, 8 -> {
                size = 4;
            }
            case 9 -> {
                size = 5;
            }
        }
        int r2 = random.nextInt(0, 60 - size);

        for (int i = 1; i < size + 1; i++) {
            for (int j = r2; j < (size) + r2; j++) {
                this.blockPositions.add(new Position(j + r2, i));
            }
        }
    }

    public void moveAsteroid() {
        for (Position p : this.blockPositions) {
            p.setY(p.getY() + 1);

        }
    }
}
