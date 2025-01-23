package pairmatching.view.component;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public abstract class InputFormView implements FormView {
    abstract void query(OutputHelper outputHelper);

    @Override
    public final void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        this.query(outputHelper);
        String command = inputHelper.readline();
        outputHelper.printNextLine();
        this.onEvent(command);
    }
}
