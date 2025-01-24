package pairmatching.view.component;

import java.util.List;
import java.util.stream.Collectors;

import pairmatching.ui.OutputHelper;

public class ReMatchingSelectFormView extends SelectFormView {
    public ReMatchingSelectFormView(SelectHandler selectHandler) {
        super(selectHandler);
    }

    @Override
    Class<? extends SelectItem> getItemClass() {
        return ReMatchingSelectItem.class;
    }

    @Override
    void show(OutputHelper outputHelper, List<SelectItem> selectItemList) {
        List<String> nameList = selectItemList.stream().map(SelectItem::getName).collect(Collectors.toList());
        outputHelper.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        outputHelper.println(String.join(" | ", nameList));
    }
}
