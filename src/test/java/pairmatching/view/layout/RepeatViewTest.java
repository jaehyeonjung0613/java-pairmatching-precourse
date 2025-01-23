package pairmatching.view.layout;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.exception.IllegalArgumentViewException;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public class RepeatViewTest {
    @Test
    void 반복_구조_동작() {
        int times = 3;
        String content = "내용";
        OutputHelper mockOutputHelper = Mockito.mock(OutputHelper.class);
        AtomicInteger count = new AtomicInteger(0);
        View view = (inputHelper, outputHelper) -> {
            outputHelper.println(content);
            if(count.getAndIncrement() < times) {
                throw new IllegalArgumentViewException("");
            }
        };
        RepeatView repeatView = new RepeatView(view);
        repeatView.execute(null, mockOutputHelper);
        Mockito.verify(mockOutputHelper, Mockito.times(times));
    }
}
