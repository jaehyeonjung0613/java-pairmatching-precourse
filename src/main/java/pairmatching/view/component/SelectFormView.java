package pairmatching.view.component;

import static pairmatching.view.component.SelectFormViewConstants.*;

import java.util.List;

import pairmatching.exception.IllegalArgumentServiceException;
import pairmatching.exception.IllegalArgumentViewException;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;

public abstract class SelectFormView implements FormView {
    private final SelectHandler selectHandler;

    public SelectFormView(SelectHandler selectHandler) {
        this.selectHandler = selectHandler;
    }

    abstract Class<? extends SelectItem> getItemClass();

    abstract void show(OutputHelper outputHelper, List<SelectItem> selectItemList);

    @Override
    public final void onEvent(String command) {
        SelectItem selectItem = SelectItem.findByCommand(this.getItemClass(), command)
            .orElseThrow(() -> new IllegalArgumentViewException(NOT_EXISTS_COMMAND_MESSAGE));
        Runnable handler = this.selectHandler.select(selectItem)
            .orElseThrow(() -> new IllegalArgumentServiceException(NOT_PROCESS_EVENT_MESSAGE));
        handler.run();
    }

    @Override
    public final void execute(InputHelper inputHelper, OutputHelper outputHelper) {
        List<SelectItem> selectItemList = SelectItem.findAll(this.getItemClass());
        this.show(outputHelper, selectItemList);
        String command = inputHelper.readline();
        outputHelper.printNextLine();
        this.onEvent(command);
    }
}
