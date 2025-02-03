package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Crew {
    private final Course course;
    private final String name;
    private final List<Pair> pairList;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
        this.pairList = new ArrayList<>();
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public List<Pair> getPairList() {
        return Collections.unmodifiableList(this.pairList);
    }

    protected void add(Pair pair) {
        this.pairList.add(pair);
    }

    protected void remove(Pair pair) {
        this.pairList.remove(pair);
    }

    public boolean existsLevelWithCrew(Level level, Crew other) {
        return this.pairList.stream()
            .filter(pair -> pair.getLevel().equals(level))
            .anyMatch(pair -> pair.contains(other));
    }

    public int count(Level level) {
        return this.pairList.stream()
            .filter(pair -> pair.getLevel().equals(level))
            .mapToInt(pair -> pair.size() - 1)
            .sum();
    }
}
