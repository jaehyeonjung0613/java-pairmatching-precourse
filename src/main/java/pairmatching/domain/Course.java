package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Course {
    BACKEND("백엔드"), FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<Course> findAll() {
        return Arrays.stream(Course.values()).collect(Collectors.toList());
    }

    public static Optional<Course> findByName(String name) {
        return findAll().stream().filter(course -> course.name.equals(name)).findFirst();
    }
}
