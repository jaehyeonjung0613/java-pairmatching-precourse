package pairmatching.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pairmatching.domain.Crew;

public class CrewRepository implements Repository<Crew> {
    private static final List<Crew> crews = new ArrayList<>();

    @Override
    public List<Crew> findAll() {
        return Collections.unmodifiableList(crews);
    }
}
