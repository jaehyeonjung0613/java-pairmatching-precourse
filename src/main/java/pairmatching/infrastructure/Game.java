package pairmatching.infrastructure;

import pairmatching.controller.view.MenuViewController;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public class Game {
    private static Game instance;

    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;
    private final MenuViewController menuViewController;

    private boolean end = false;

    private Game(InputHelper inputHelper, OutputHelper outputHelper) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
        this.menuViewController = new MenuViewController(inputHelper, outputHelper, this::end);
    }

    protected static Game getInstance(InputHelper inputHelper, OutputHelper outputHelper) {
        if (instance == null) {
            instance = new Game(inputHelper, outputHelper);
        }
        return instance;
    }

    public void run() {
        View menuView = menuViewController.make();
        do {
            try {
                menuView.execute(inputHelper, outputHelper);
            } catch (IllegalArgumentException e) {
                outputHelper.printError(e.getMessage());
                outputHelper.printNextLine();
            }
        } while (!this.end);
    }

    private void end() {
        this.end = true;
    }

    public void finish() {
        instance = null;
    }
}
