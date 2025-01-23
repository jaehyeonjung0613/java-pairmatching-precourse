package pairmatching.view.layout;

import pairmatching.exception.IllegalArgumentBeanException;
import pairmatching.exception.IllegalArgumentViewException;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;

public final class RepeatView implements View {
    private final View view;

    public RepeatView(View view) {
        this.view = view;
    }

    @Override
    public void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        do {
            try {
                view.execute(inputHelper, outputHelper);
                return;
            } catch (IllegalArgumentViewException e) {
                outputHelper.printError(e.getMessage());
                outputHelper.printNextLine();
            } catch (IllegalArgumentBeanException ignored) {
            }
        } while (true);
    }
}
