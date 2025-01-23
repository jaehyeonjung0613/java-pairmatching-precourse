package pairmatching.view.component;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class MissionInputFormViewTest {
    @Test
    void 텍스트_입력_동작() {
        String parameter = "test";
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        Consumer<String> handler = Mockito.mock(Consumer.class);
        MissionInputFormView missionInputFormView = new MissionInputFormView(handler);
        Mockito.when(inputHelper.readline()).thenReturn(parameter);
        missionInputFormView.execute(inputHelper, outputHelper);
        Mockito.verify(outputHelper).println("과정, 레벨, 미션을 선택하세요.");
        Mockito.verify(outputHelper).println("ex) 백엔드, 레벨1, 자동차경주");
        Mockito.verify(handler, Mockito.atMostOnce()).accept(parameter);
    }
}
