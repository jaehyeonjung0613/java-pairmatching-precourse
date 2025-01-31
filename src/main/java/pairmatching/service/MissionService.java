package pairmatching.service;

import static pairmatching.service.MissionServiceConstants.*;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.exception.IllegalArgumentServiceException;
import pairmatching.repository.CrewRepository;

public class MissionService implements Service {
    private final CrewRepository crewRepository = new CrewRepository();

    public List<Pair> select(Course course, Mission mission) {
        return mission.getPairList(course)
            .orElseThrow(() -> new IllegalArgumentServiceException(NOT_EXISTS_MATCHING_HISTORY_MESSAGE));
    }

    public void match(Course course, Mission mission) {
        List<Crew> crewList = this.crewRepository.findAllByCourse(course);
        List<Crew> shuffledCrewList = this.shuffleCrewList(crewList);
        mission.match(course, shuffledCrewList);
    }

    private List<Crew> shuffleCrewList(List<Crew> crewList) {
        return Randoms.shuffle(crewList);
    }
}
