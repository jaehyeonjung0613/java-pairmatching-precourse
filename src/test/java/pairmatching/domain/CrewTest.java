package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CrewTest {
    @Test
    void 저장_및_반환() {
        Course course = Course.FRONTEND;
        String name = "anonymous";
        Crew crew = new Crew(course, name);
        assertThat(crew.getCourse()).isEqualTo(course);
        assertThat(crew.getName()).isEqualTo(name);
    }
}
