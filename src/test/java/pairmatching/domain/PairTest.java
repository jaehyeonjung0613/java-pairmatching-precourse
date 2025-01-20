package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

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
}
