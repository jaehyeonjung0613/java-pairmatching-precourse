package pairmatching.view.layout;

import pairmatching.view.View;

public class SharpFrameView extends FrameView {
    public SharpFrameView(View view) {
        super(view);
    }

    @Override
    String frame() {
        return "#############################################";
    }
}
