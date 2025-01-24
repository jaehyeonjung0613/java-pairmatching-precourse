package pairmatching.view.component;

public enum ReMatchingSelectItem implements SelectItem{
    YES("네", "네"),
    NO("아니오", "아니오");

    private final String command;
    private final String name;

    ReMatchingSelectItem(String command, String name) {
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
