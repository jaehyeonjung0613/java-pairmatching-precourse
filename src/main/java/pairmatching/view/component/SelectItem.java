package pairmatching.view.component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface SelectItem {
    String getCommand();

    String getName();

    static List<SelectItem> findAll(Class<? extends SelectItem> selectItemClass) {
        return Arrays.stream(selectItemClass.getEnumConstants()).collect(Collectors.toList());
    }

    static Optional<SelectItem> findByCommand(Class<? extends SelectItem> selectItemClass, String command) {
        List<SelectItem> selectItemList = findAll(selectItemClass);
        for (SelectItem selectItem : selectItemList) {
            if (selectItem.getCommand().equals(command)) {
                return Optional.of(selectItem);
            }
        }
        return Optional.empty();
    }
}
