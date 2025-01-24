package pairmatching.view.component;

public enum MenuSelectItem implements SelectItem {
    PAIR_MATCHING("1", "페어 매칭"),
    PAIR_SELECTION("2", "페어 조회"),
    PAIR_RESET("3", "페어 초기화"),
    END("Q", "종료");

    private final String command;
    private final String name;

    MenuSelectItem(String command, String name) {
        this.command = command;
        this.name = name;
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
