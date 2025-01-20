package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;
import static pairmatching.domain.PairConstants.*;

import org.junit.jupiter.api.Test;

public class PairTest {
    @Test
    void 저장_및_반환() {
        Level level = Level.LEVEL1;
        Pair pair = new Pair(level);
        assertThat(pair.getLevel()).isEqualTo(level);
        assertThat(pair.getCrewList()).isEmpty();
    }

    @Test
    void 페어_내_크루_추가() {
        Pair pair = new Pair(Level.LEVEL1);
        Crew crew = new Crew(Course.FRONTEND, "test");
        pair.add(crew);
        assertThat(pair.getCrewList()).hasSize(1);
    }

    @Test
    void 페어_내_크루_삭제() {
        Pair pair = new Pair(Level.LEVEL1);
        Crew crew = new Crew(Course.FRONTEND, "test");
        pair.add(crew);
        assertThat(pair.getCrewList()).hasSize(1);
        pair.remove(crew);
        assertThat(pair.getCrewList()).isEmpty();
    }

    @Test
    void 다른_레벨_동일_페어_매칭() {
        Pair pairA = new Pair(Level.LEVEL1), pairB = new Pair(Level.LEVEL2);
        Crew crewA = new Crew(Course.FRONTEND, "testA"), crewB = new Crew(Course.FRONTEND, "testB");
        pairA.add(crewA);
        pairA.add(crewB);
        pairA.save();
        pairB.add(crewB);
        pairB.add(crewA);
        pairB.save();
        assertThat(pairB.getCrewList()).hasSize(2);
    }

    @Test
    void 동일_레벨_동일_페어_매칭() {
        Level level = Level.LEVEL1;
        Pair pairA = new Pair(level), pairB = new Pair(level);
        Crew crewA = new Crew(Course.FRONTEND, "testA"), crewB = new Crew(Course.FRONTEND, "testB");
        pairA.add(crewA);
        pairA.add(crewB);
        pairA.save();
        pairB.add(crewB);
        assertThatThrownBy(() -> pairB.add(crewA)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(EXISTS_SAME_LEVEL_PAIR_MATCHING_MESSAGE);
        assertThat(pairB.getCrewList()).hasSize(1);
    }
}
