package pairmatching.ui;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleInputHelper extends InputHelper {
    @Override
    public String readline() {
        return Console.readLine();
    }
}
