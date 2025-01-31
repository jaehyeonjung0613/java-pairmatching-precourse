package pairmatching.controller.view;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;
import pairmatching.view.component.MenuSelectFormView;
import pairmatching.view.component.MenuSelectItem;
import pairmatching.view.component.SelectHandler;

public class MenuViewController implements ViewController {
    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;
    private final Runnable endHandler;

    public MenuViewController(InputHelper inputHelper, OutputHelper outputHelper, Runnable endHandler) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
        this.endHandler = endHandler;
    }

    @Override
    public View make() {
        return new MenuSelectFormView(SelectHandler.builder()
            .addEventListener(MenuSelectItem.END, this.endHandler));
    }
}
