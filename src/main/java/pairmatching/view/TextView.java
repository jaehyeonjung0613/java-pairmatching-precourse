package pairmatching.view;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public abstract class TextView implements View {
    abstract void show(OutputHelper outputHelper);

    @Override
    public final void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        this.show(outputHelper);
    }
}
