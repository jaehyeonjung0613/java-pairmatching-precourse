package pairmatching.service;

import static pairmatching.service.MissionServiceConstants.*;

import java.util.List;

import pairmatching.domain.Course;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.exception.IllegalArgumentServiceException;

public class MissionService implements Service {

    public List<Pair> select(Course course, Mission mission) {
        return mission.getPairList(course)
            .orElseThrow(() -> new IllegalArgumentServiceException(NOT_EXISTS_MATCHING_HISTORY_MESSAGE));
    }
}
