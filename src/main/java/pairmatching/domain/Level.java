package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Level {
    LEVEL1("레벨1"), LEVEL2("레벨2"), LEVEL3("레벨3"), LEVEL4("레벨4"), LEVEL5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<Level> findAll() {
        return Arrays.stream(Level.values()).collect(Collectors.toList());
    }

    public static Optional<Level> findByName(String name) {
        return findAll().stream().filter(level -> level.name.equals(name)).findFirst();
    }
}
