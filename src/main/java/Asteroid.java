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
        int r2 = random.nextInt(0, 60 - size);

        for (int i = 0; i < size; i++) {
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
