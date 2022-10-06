import Posistion.Position;

import java.util.ArrayList;
import java.util.Random;

public class Asteroids {

    ArrayList<Position> positions;
    final char block= 9608;
    int size;
    public Asteroids() {
        Random random = new Random();
        int i = random.nextInt(0, 10);
        switch(i){
            case 0,1,2-> {
              size=2;
            }
            case 3,4,5->{
                size=3;
            }
            case 6,7->{
                size=1;
            }
            case 8,9->{
                size=5;
            }
        }

    }
}
