package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    private final Level level;
    private final List<Crew> crewList;

    public Pair(Level level) {
        this.level = level;
        this.crewList = new ArrayList<>();
    }

    public Level getLevel() {
        return level;
    }

    public List<Crew> getCrewList() {
        return Collections.unmodifiableList(this.crewList);
    }

    public void add(Crew crew) {
        this.crewList.add(crew);
    }

    public void remove(Crew crew) {
        this.crewList.remove(crew);
    }
}
