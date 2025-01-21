package pairmatching.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.OutputHelper;

public class CourseTextViewTest {
    @Test
    void 과정_출력() {
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        CourseTextView courseTextView = new CourseTextView();
        String message = "과정: 백엔드 | 프론트엔드";
        courseTextView.show(outputHelper);
        Mockito.verify(outputHelper).println(message);
    }
}
