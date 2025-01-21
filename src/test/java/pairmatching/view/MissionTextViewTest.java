package pairmatching.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.OutputHelper;

public class MissionTextViewTest {
    @Test
    void 미션_출력() {
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        MissionTextView missionTextView = new MissionTextView();
        missionTextView.show(outputHelper);
        Mockito.verify(outputHelper).println("미션:");
        Mockito.verify(outputHelper).println(" - 레벨1: 자동차경주 | 로또 | 숫자야구게임");
    }
}
