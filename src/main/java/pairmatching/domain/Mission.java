package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import pairmatching.Config;

public enum Mission {
    RACING(Level.LEVEL1, "자동차경주"), LOTTO(Level.LEVEL1, "로또"), BASEBALL(Level.LEVEL1, "숫자야구게임"), CART(Level.LEVEL2,
        "장바구니"), PAYMENT(Level.LEVEL2, "결제"), SUBWAY(Level.LEVEL2, "지하철노선도"), PERFORMANCE(Level.LEVEL4,
        "성능개선"), DEPLOYMENT(Level.LEVEL4, "배포");

    private final Level level;
    private final String name;
    private final Map<Course, List<Pair>> pairOfCourse;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
        this.pairOfCourse = new HashMap<>();
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

    public static List<Mission> findAllByLevel(Level level) {
        return findAll().stream().filter(mission -> mission.getLevel().equals(level)).collect(Collectors.toList());
    }

    public static Optional<Mission> findByLevelAndName(Level level, String name) {
        Mission[] missions = Mission.values();
        return Arrays.stream(missions)
            .filter(mission -> mission.getLevel().equals(level) && mission.getName().equals(name))
            .findFirst();
    }

    public boolean exists(Course course) {
        return this.pairOfCourse.get(course) != null;
    }

    public void match(Course course, List<Crew> shuffledCrewList) {
        List<Pair> pairList = this.divide(shuffledCrewList);
        this.remove(course);
        this.pairOfCourse.put(course, pairList);
        this.generate(course);
    }

    private List<Pair> divide(List<Crew> shuffledCrewList) {
        List<Pair> pairList = new ArrayList<>();
        int length = shuffledCrewList.size();
        for (int fromIndex = 0, toIndex = Config.PAIR_PER_MIN_COUNT;
             fromIndex < length; fromIndex = toIndex, toIndex += Config.PAIR_PER_MIN_COUNT) {
            Pair pair = new Pair(this.level);
            if (this.isLastPair(fromIndex, length)) {
                toIndex = length;
            }
            for (int index = fromIndex; index < toIndex; index++) {
                pair.add(shuffledCrewList.get(index));
            }
            pairList.add(pair);
        }
        return pairList;
    }

    private void remove(Course course) {
        List<Pair> pairList = this.pairOfCourse.remove(course);
        if (pairList != null) {
            for (Pair pair : pairList) {
                pair.clear();
            }
        }
    }

    private boolean isLastPair(int index, int length) {
        return index + Config.PAIR_PER_MIN_COUNT * 2 - 1 >= length;
    }

    private void generate(Course course) {
        if (this.exists(course)) {
            List<Pair> pairList = this.pairOfCourse.get(course);
            for (Pair pair : pairList) {
                pair.save();
            }
        }
    }

    public Optional<List<Pair>> getPairList(Course course) {
        if (this.exists(course)) {
            return Optional.of(Collections.unmodifiableList(this.pairOfCourse.get(course)));
        }
        return Optional.empty();
    }
}
