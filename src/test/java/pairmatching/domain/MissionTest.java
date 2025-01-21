package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import pairmatching.Config;

public class MissionTest {
    private static final String[] names = {"자동차경주", "로또", "숫자야구게임", "장바구니", "결제", "지하철노선도", "성능개선", "배포"};

    @Test
    void 전체_조회() {
        List<String> nameList = Mission.findAll().stream().map(Mission::getName).collect(Collectors.toList());
        assertThat(nameList).containsExactly(names);
    }

    @Test
    void 존재_단건_조회() {
        Optional<Mission> optionalMission = Mission.findByLevelAndName(Level.LEVEL1, "자동차경주");
        assertThat(optionalMission.isPresent()).isEqualTo(true);
    }

    @Test
    void 미존재_단건_조회() {
        Optional<Mission> optionalMission = Mission.findByLevelAndName(Level.LEVEL1, "NOT EXISTS");
        assertThat(optionalMission.isPresent()).isEqualTo(false);
    }

    @Test
    void 페어_기본_매칭() {
        Course course = Course.FRONTEND;
        Mission mission = Mission.RACING;
        assertThat(mission.exists(course)).isEqualTo(false);
        mission.match(course, this.createCrewList(course, Config.PAIR_PER_MIN_COUNT * 2));
        assertThat(mission.exists(course)).isEqualTo(true);
        List<Pair> pairList = mission.getPairList(course).orElse(null);
        assertThat(pairList).hasSize(2);
    }

    @Test
    void 페어_홀수_포함_매칭() {
        Course course = Course.FRONTEND;
        Mission mission = Mission.RACING;
        assertThat(mission.exists(course)).isEqualTo(false);
        mission.match(course, this.createCrewList(course, Config.PAIR_PER_MIN_COUNT * 2 + 1));
        assertThat(mission.exists(course)).isEqualTo(true);
        List<Pair> pairList = mission.getPairList(course).orElse(null);
        assertThat(pairList).hasSize(2);
    }

    private List<Crew> createCrewList(Course course, int count) {
        return IntStream.range(0, count)
            .mapToObj(number -> new Crew(course, String.valueOf(number)))
            .collect(Collectors.toList());
    }

    @Test
    void 페어_재_매칭() {
        Course course = Course.FRONTEND;
        Mission mission = Mission.RACING;
        Crew crew = new Crew(course, "test");
        List<Crew> crewListA = this.createCrewList(course, Config.PAIR_PER_MIN_COUNT);
        List<Crew> crewListB = this.createCrewList(course, Config.PAIR_PER_MIN_COUNT);
        crewListA.add(crew);
        crewListB.add(crew);
        mission.match(course, crewListA);
        mission.match(course, crewListB);
        assertThat(crew.getPairList()).hasSize(1);
    }
}
