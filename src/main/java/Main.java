import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        playGame();

    }
    static void playGame() throws IOException {

        Terminal terminal = terminalBuilder();
    }

    static Terminal terminalBuilder() {

        boolean isDead = false;
        int score = 0;


        try {
            TerminalSize ts = new TerminalSize (60, 50);
                                                                            //       TerminalPosition tp = new TerminalPosition();
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setInitialTerminalSize(ts);
            Terminal terminal = terminalFactory.createTerminal();
            return terminal;

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return null;


        while(!isDead) {
            String scoreString = String.format("Score: %04d", score);

            for(int i = 0; i < scoreString.length(); ++i) {
                terminal.setCursorPosition(49 + i, 0);
                terminal.putCharacter(scoreString.charAt(i));

                }
    }


        }
    }


}