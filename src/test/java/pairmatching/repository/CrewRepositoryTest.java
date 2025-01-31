package pairmatching.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CrewRepositoryTest {
    @Test
    void 크루_목록_초기화() {
        CrewRepository crewRepository = new CrewRepository();
        assertThat(crewRepository.findAll()).isEmpty();
        CrewRepository.init();
        assertThat(crewRepository.findAll()).hasSize(35);
    }
}
