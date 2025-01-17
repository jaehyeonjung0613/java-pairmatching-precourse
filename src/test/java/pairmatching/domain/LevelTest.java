package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class LevelTest {
    private static final String[] names = {"레벨1", "레벨2", "레벨3", "레벨4", "레벨5"};

    @Test
    void 전체_조회() {
        List<String> nameList = Level.findAll().stream().map(Level::getName).collect(Collectors.toList());
        assertThat(nameList).containsExactly(names);
    }

    @Test
    void 존재_단건_조회() {
        Optional<Level> optionalLevel = Level.findByName(names[0]);
        assertThat(optionalLevel.isPresent()).isEqualTo(true);
    }

    @Test
    void 미존재_단건_조회() {
        Optional<Level> optionalLevel = Level.findByName("NOT EXISTS");
        assertThat(optionalLevel.isPresent()).isEqualTo(false);
    }
}
