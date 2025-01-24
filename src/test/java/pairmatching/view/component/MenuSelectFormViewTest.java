package pairmatching.view.component;

import static org.assertj.core.api.Assertions.*;
import static pairmatching.view.component.SelectFormViewConstants.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public class MenuSelectFormViewTest {
    @Test
    void 올바른_선택_입력_동작() {
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        AtomicInteger selected = new AtomicInteger(0);
        MenuSelectFormView menuSelectFormView = new MenuSelectFormView(
            SelectHandler.builder().addEventListener(MenuSelectItem.PAIR_MATCHING, selected::getAndIncrement));
        Mockito.when(inputHelper.readline()).thenReturn("1");
        menuSelectFormView.execute(inputHelper, outputHelper);
        assertThat(selected.get()).isEqualTo(1);
        Mockito.verify(outputHelper).println("기능을 선택하세요.");
        Mockito.verify(outputHelper).println("1. 페어 매칭");
    }

    @Test
    void 올바른_않은_선택_입력_동작() {
        InputHelper inputHelper = Mockito.mock(InputHelper.class);
        OutputHelper outputHelper = Mockito.mock(OutputHelper.class);
        MenuSelectFormView menuSelectFormView = new MenuSelectFormView(
            SelectHandler.builder().addEventListener(MenuSelectItem.PAIR_MATCHING, () -> {
            }));
        Mockito.when(inputHelper.readline()).thenReturn("NOT VALID COMMAND");
        assertThatThrownBy(() -> menuSelectFormView.execute(inputHelper, outputHelper))
            .isInstanceOf(IllegalArgumentException.class).hasMessage(NOT_EXISTS_COMMAND_MESSAGE);
    }
}
