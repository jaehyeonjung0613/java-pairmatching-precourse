package pairmatching.infrastructure;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class Game {
    private static Game instance;

    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;

    private Game(InputHelper inputHelper, OutputHelper outputHelper) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
    }

    protected static Game getInstance(InputHelper inputHelper, OutputHelper outputHelper) {
        if (instance == null) {
            instance = new Game(inputHelper, outputHelper);
        }
        return instance;
    }

    public void run() {
    }

    public void finish() {
        instance = null;
    }
}
