import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

    }

    static void playGame() throws IOException {

        Terminal terminal = terminalBuilder();
    }

    static Terminal terminalBuilder() {

        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            Terminal terminal = terminalFactory.createTerminal();
            return terminal;
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return null;
        }

    }

}