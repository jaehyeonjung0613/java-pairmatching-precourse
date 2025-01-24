package pairmatching.view.component;

import java.util.List;

import pairmatching.ui.OutputHelper;

public class MenuSelectFormView extends SelectFormView {

    public MenuSelectFormView(SelectHandler selectHandler) {
        super(selectHandler);
    }

    @Override
    Class<? extends SelectItem> getItemClass() {
        return MenuSelectItem.class;
    }

    @Override
    void show(OutputHelper outputHelper, List<SelectItem> selectItemList) {
        outputHelper.println("기능을 선택하세요.");
        for (SelectItem selectItem : selectItemList) {
            outputHelper.println(String.format("%s. %s", selectItem.getCommand(), selectItem.getName()));
        }
    }
}
