package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

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
}
