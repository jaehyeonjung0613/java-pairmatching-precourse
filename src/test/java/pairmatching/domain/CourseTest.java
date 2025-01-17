package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class CourseTest {
    private static final String[] names = {"백엔드", "프론트엔드"};

    @Test
    void 전체_조회() {
        List<String> nameList = Course.findAll().stream().map(Course::getName).collect(Collectors.toList());
        assertThat(nameList).containsExactly(names);
    }

    @Test
    void 존재_단건_조회() {
        Optional<Course> optionalCourse = Course.findByName(names[0]);
        assertThat(optionalCourse.isPresent()).isEqualTo(true);
    }

    @Test
    void 미존재_단건_조회() {
        Optional<Course> optionalCourse = Course.findByName("NOT EXISTS");
        assertThat(optionalCourse.isPresent()).isEqualTo(false);
    }
}
