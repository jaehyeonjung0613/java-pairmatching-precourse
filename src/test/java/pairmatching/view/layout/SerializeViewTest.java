package pairmatching.view.layout;

import static org.mockito.Mockito.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public class SerializeViewTest {
    @Test
    void 연속_시행_구조_동작() {
        OutputHelper mockOutputHelper = Mockito.mock(OutputHelper.class);
        AtomicInteger count = new AtomicInteger(0);
        View view = (inputHelper, outputHelper) -> outputHelper.println(String.valueOf(count.getAndIncrement()));
        SerializeView serializeView = new SerializeView(view, view, view);
        serializeView.execute(null, mockOutputHelper);
        InOrder inOrder = inOrder(mockOutputHelper);
        for (int i = 0; i < 3; i++) {
            inOrder.verify(mockOutputHelper).println(String.valueOf(i));
        }
    }
}
