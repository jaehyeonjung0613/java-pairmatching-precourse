package pairmatching.view.layout;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public abstract class FrameView implements View {
    private final View view;

    public FrameView(View view) {
        this.view = view;
    }

    abstract String frame();

    @Override
    public final void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        String frame = this.frame();
        outputHelper.println(frame);
        this.view.execute(inputHelper, outputHelper);
        outputHelper.println(frame);
    }
}
