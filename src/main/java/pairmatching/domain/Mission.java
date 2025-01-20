package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Mission {
    RACING(Level.LEVEL1, "자동차경주"), LOTTO(Level.LEVEL1, "로또"), BASEBALL(Level.LEVEL1, "숫자야구게임"), CART(Level.LEVEL2,
        "장바구니"), PAYMENT(Level.LEVEL2, "결제"), SUBWAY(Level.LEVEL2, "지하철노선도"), PERFORMANCE(Level.LEVEL4,
        "성능개선"), DEPLOYMENT(Level.LEVEL4, "배포");

    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public static List<Mission> findAll() {
        return Arrays.stream(Mission.values()).collect(Collectors.toList());
    }

    public static Optional<Mission> findByLevelAndName(Level level, String name) {
        Mission[] missions = Mission.values();
        return Arrays.stream(missions)
            .filter(mission -> mission.getLevel().equals(level) && mission.getName().equals(name))
            .findFirst();
    }
}
