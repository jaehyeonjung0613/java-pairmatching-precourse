package pairmatching.domain;

import static pairmatching.domain.PairConstants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pairmatching.exception.IllegalArgumentServiceException;

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

    public void add(Crew crew) throws IllegalArgumentException {
        this.validateMatchingWithCrew(crew);
        this.crewList.add(crew);
    }

    private void validateMatchingWithCrew(Crew other) {
        if (this.crewList.stream()
            .anyMatch(
                crew -> crew.existsLevelWithCrew(this.level, other) || other.existsLevelWithCrew(this.level, crew))) {
            throw new IllegalArgumentServiceException(EXISTS_SAME_LEVEL_PAIR_MATCHING_MESSAGE);
        }
    }

    public void remove(Crew crew) {
        this.crewList.remove(crew);
    }

    public boolean contains(Crew other) {
        return this.crewList.contains(other);
    }

    protected void save() {
        for (Crew crew : this.crewList) {
            crew.add(this);
        }
    }

    public void clear() {
        for (Crew crew : this.crewList) {
            crew.remove(this);
        }
        this.crewList.clear();
    }
}
