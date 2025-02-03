package pairmatching.controller;

import static pairmatching.controller.MissionControllerConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.exception.IllegalArgumentViewException;
import pairmatching.service.MissionService;

public class MissionController implements Controller {
    private final MissionService missionService = new MissionService();

    public boolean checkPairMatched(String input) {
        Command command = new Command(input);
        Course course = this.getOneByCourse(command.getCourseName());
        Level level = this.getOneByLevel(command.getLevelName());
        Mission mission = this.getOneByMission(level, command.getMissionName());
        return mission.exists(course);
    }

    public void match(String input) {
        Command command = new Command(input);
        Course course = this.getOneByCourse(command.getCourseName());
        Level level = this.getOneByLevel(command.getLevelName());
        Mission mission = this.getOneByMission(level, command.getMissionName());
        this.missionService.match(course, mission);
    }

    public List<List<String>> select(String input) {
        Command command = new Command(input);
        Course course = this.getOneByCourse(command.getCourseName());
        Level level = this.getOneByLevel(command.getLevelName());
        Mission mission = this.getOneByMission(level, command.getMissionName());
        List<Pair> pairList = this.missionService.select(course, mission);
        List<List<String>> pairOfNameList = new ArrayList<>();
        for (Pair pair : pairList) {
            List<String> nameList = pair.getCrewList().stream().map(Crew::getName).collect(Collectors.toList());
            pairOfNameList.add(nameList);
        }
        return pairOfNameList;
    }

    private Course getOneByCourse(String courseName) {
        return Course.findByName(courseName)
            .orElseThrow(() -> new IllegalArgumentViewException(NOT_EXISTS_COURSE_INPUT_MESSAGE));
    }

    private Level getOneByLevel(String levelName) {
        return Level.findByName(levelName)
            .orElseThrow(() -> new IllegalArgumentViewException(NOT_EXISTS_LEVEL_INPUT_MESSAGE));
    }

    private Mission getOneByMission(Level level, String missionName) {
        return Mission.findByLevelAndName(level, missionName)
            .orElseThrow(() -> new IllegalArgumentViewException(NOT_EXISTS_MISSION_INPUT_MESSAGE));
    }

    public void init() {
        List<Course> courseList = Course.findAll();
        List<Mission> missionList = Mission.findAll();
        for(Mission mission : missionList) {
            for(Course course : courseList) {
                mission.remove(course);
            }
        }
    }

    private static class Command {
        private final String courseName;
        private final String levelName;
        private final String missionName;

        public Command(String command) {
            this.validateCommand(command);
            String[] commands = command.split(MISSION_INPUT_SEPARATOR);
            this.courseName = commands[0];
            this.levelName = commands[1];
            this.missionName = commands[2];
        }

        private void validateCommand(String command) {
            if (command.split(MISSION_INPUT_SEPARATOR).length < 3) {
                throw new IllegalArgumentViewException(NOT_VALID_MISSION_INPUT_MESSAGE);
            }
        }

        public String getCourseName() {
            return courseName;
        }

        public String getLevelName() {
            return levelName;
        }

        public String getMissionName() {
            return missionName;
        }
    }
}
