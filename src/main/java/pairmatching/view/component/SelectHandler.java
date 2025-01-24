package pairmatching.view.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SelectHandler {
    private final Map<SelectItem, Runnable> handlerMap = new HashMap<>();

    private SelectHandler() {
    }

    public static SelectHandler builder() {
        return new SelectHandler();
    }

    public SelectHandler addEventListener(SelectItem selectItem, Runnable handler) {
        this.handlerMap.put(selectItem, handler);
        return this;
    }

    public Optional<Runnable> select(SelectItem selectItem) {
        return Optional.ofNullable(this.handlerMap.get(selectItem));
    }
}
