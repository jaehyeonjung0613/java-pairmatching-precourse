package pairmatching.ui;

import java.io.PrintStream;

public class ConsoleOutputHelper extends OutputHelper {
    private final PrintStream console = System.out;

    @Override
    public void print(String message) {
        this.console.print(message);
    }

    @Override
    public void println(String message) {
        this.console.println(message);
    }
}
