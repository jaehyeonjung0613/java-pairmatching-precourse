package pairmatching.service;

import static pairmatching.service.MissionServiceConstants.*;

import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.Config;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
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
        if (!this.checkPossibleMatching(mission.getLevel(), crewList)) {
            throw new IllegalArgumentServiceException(NOT_EXISTS_MATCHING_APPROACH_MESSAGE);
        }
        if (this.tryMatching(course, mission, crewList) > MAX_MATCHING_COUNT) {
            throw new IllegalArgumentServiceException(NOT_MATCH_MAX_COUNT_MESSAGE);
        }
    }

    private boolean checkPossibleMatching(Level level, List<Crew> crewList) {
        int totalCount = crewList.size();
        int totalGroupPerCount = this.getGroupPerCount(totalCount), totalLastGroupPerCount = this.getLastGroupPerCount(
            totalCount);
        int groupPerCount = 0, lastGroupPerCount = 0;
        List<Integer> countOfPossibleMatchingList = this.getCountOfPossibleMatchingList(level, crewList);
        for (int matchingCount : countOfPossibleMatchingList) {
            if (lastGroupPerCount < totalLastGroupPerCount && matchingCount >= totalLastGroupPerCount) {
                lastGroupPerCount++;
                continue;
            }
            groupPerCount++;
        }
        return groupPerCount == totalGroupPerCount && lastGroupPerCount == totalLastGroupPerCount;
    }

    private List<Integer> getCountOfPossibleMatchingList(Level level, List<Crew> crewList) {
        return crewList.stream()
            .map(crew -> crewList.size() - crew.count(level))
            .filter(count -> count >= Config.PAIR_PER_MIN_COUNT - 1)
            .collect(Collectors.toList());
    }

    private int getGroupPerCount(int totalCount) {
        return (totalCount / Config.PAIR_PER_MIN_COUNT - 1) * Config.PAIR_PER_MIN_COUNT;
    }

    private int getLastGroupPerCount(int totalCount) {
        return Config.PAIR_PER_MIN_COUNT + totalCount % Config.PAIR_PER_MIN_COUNT;
    }

    private int tryMatching(Course course, Mission mission, List<Crew> crewList) {
        int count = 1;
        do {
            try {
                List<Crew> shuffledCrewList = this.shuffleCrewList(crewList);
                mission.match(course, shuffledCrewList);
                break;
            } catch (IllegalArgumentServiceException ignored) {
            }
        } while (count++ < MAX_MATCHING_COUNT);
        return count;
    }

    private List<Crew> shuffleCrewList(List<Crew> crewList) {
        return Randoms.shuffle(crewList);
    }
}
