package pairmatching.view.layout;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public final class SerializeView implements View {
    private final View[] views;

    public SerializeView(View... views) {
        this.views = views;
    }

    @Override
    public void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        for (View view : this.views) {
            view.execute(inputHelper, outputHelper);
        }
    }
}
