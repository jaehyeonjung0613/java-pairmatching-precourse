package pairmatching.view.component;

import static org.assertj.core.api.Assertions.*;
import static pairmatching.view.component.SelectFormViewConstants.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class ReMatchingSelectFormViewTest {
    @Test
    void 올바른_선택_입력_동작() {
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        AtomicInteger selected = new AtomicInteger(0);
        ReMatchingSelectFormView reMatchingSelectFormView = new ReMatchingSelectFormView(
            SelectHandler.builder().addEventListener(ReMatchingSelectItem.YES, selected::getAndIncrement));
        Mockito.when(inputHelper.readline()).thenReturn("네");
        reMatchingSelectFormView.execute(inputHelper, outputHelper);
        assertThat(selected.get()).isEqualTo(1);
        Mockito.verify(outputHelper).println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        Mockito.verify(outputHelper).println("네 | 아니오");
    }

    @Test
    void 올바른_않은_선택_입력_동작() {
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        ReMatchingSelectFormView reMatchingSelectFormView = new ReMatchingSelectFormView(
            SelectHandler.builder().addEventListener(MenuSelectItem.PAIR_MATCHING, () -> {
            }));
        Mockito.when(inputHelper.readline()).thenReturn("NOT VALID COMMAND");
        assertThatThrownBy(() -> reMatchingSelectFormView.execute(inputHelper, outputHelper))
            .isInstanceOf(IllegalArgumentException.class).hasMessage(NOT_EXISTS_COMMAND_MESSAGE);
    }
}
