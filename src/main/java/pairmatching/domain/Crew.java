package pairmatching.domain;

import java.util.ArrayList;
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

    protected void add(Pair pair) {
        this.pairList.add(pair);
    }

    public boolean existsLevelWithCrew(Level level, Crew other) {
        return this.pairList.stream().filter(pair -> pair.getLevel().equals(level))
            .anyMatch(pair -> pair.contains(other));
    }
}
