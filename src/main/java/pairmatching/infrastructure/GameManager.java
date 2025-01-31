package pairmatching.infrastructure;

import pairmatching.ui.ConsoleInputHelper;
import pairmatching.ui.ConsoleOutputHelper;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class GameManager {
    private InputHelper inputHelper = new ConsoleInputHelper();
    private OutputHelper outputHelper = new ConsoleOutputHelper();

    public GameManager inputHelper(InputHelper inputHelper) {
        this.inputHelper = inputHelper;
        return this;
    }

    public GameManager outputHelper(OutputHelper outputHelper) {
        this.outputHelper = outputHelper;
        return this;
    }

    public Game build() {
        return Game.getInstance(this.inputHelper, this.outputHelper);
    }
}
