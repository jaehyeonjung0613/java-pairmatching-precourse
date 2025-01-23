package pairmatching.view.component;

import java.util.function.Consumer;

import pairmatching.ui.OutputHelper;

public class MissionInputFormView extends InputFormView {
    private final Consumer<String> handler;

    public MissionInputFormView(Consumer<String> handler) {
        this.handler = handler;
    }

    @Override
    void query(OutputHelper outputHelper) {
        outputHelper.println("과정, 레벨, 미션을 선택하세요.");
        outputHelper.println("ex) 백엔드, 레벨1, 자동차경주");
    }

    @Override
    public void onEvent(String command) {
        this.handler.accept(command);
    }
}
