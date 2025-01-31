package pairmatching.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class CrewRepository implements Repository<Crew> {
    private static final List<Crew> crews = new ArrayList<>();

    public static void init() {
        List<Resource> resourceList = Resource.findAll();
        for (Resource resource : resourceList) {
            List<String> crewNameList = loadCrewName(resource.getFilename());
            for (String crewName : crewNameList) {
                Crew crew = new Crew(resource.getCourse(), crewName);
                crews.add(crew);
            }
        }
    }

    private static List<String> loadCrewName(String filename) {
        List<String> crewNameList = new ArrayList<>();
        String filePath = Objects.requireNonNull(CrewRepository.class.getClassLoader().getResource(filename)).getPath();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                crewNameList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return crewNameList;
    }

    @Override
    public List<Crew> findAll() {
        return Collections.unmodifiableList(crews);
    }

    public List<Crew> findAllByCourse(Course course) {
        return crews.stream().filter(crew -> crew.getCourse().equals(course)).collect(Collectors.toList());
    }

    private enum Resource {
        BACKEND(Course.BACKEND, "backend-crew.md"), FRONTEND(Course.FRONTEND, "frontend-crew.md");

        private final Course course;
        private final String filename;

        Resource(Course course, String filename) {
            this.course = course;
            this.filename = filename;
        }

        public Course getCourse() {
            return course;
        }

        public String getFilename() {
            return filename;
        }

        public static List<Resource> findAll() {
            return Arrays.stream(Resource.values()).collect(Collectors.toList());
        }
    }
}
