package pairmatching.view.layout;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public class SharpFrameViewTest {
    @Test
    void 샵_템플릿_출력() {
        String frame = "#############################################";
        String content = "내용";
        OutputHelper mockOutputHelper = Mockito.mock(OutputHelper.class);
        View view = (inputHelper, outputHelper) -> outputHelper.println(content);
        SharpFrameView sharpFrameView = new SharpFrameView(view);
        sharpFrameView.execute(null, mockOutputHelper);
        Mockito.verify(mockOutputHelper, Mockito.times(2)).println(frame);
        Mockito.verify(mockOutputHelper).println(content);
    }
}
