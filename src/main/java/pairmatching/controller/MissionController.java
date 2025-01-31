package pairmatching.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.service.MissionService;

public class MissionController implements Controller {
    private final MissionService missionService = new MissionService();

    public List<List<String>> select(String input) {
        String[] commands = input.split(", ");
        String courseName = commands[0];
        String levelName = commands[1];
        String missionName = commands[2];
        Course course = this.getOneByCourse(courseName);
        Level level = this.getOneByLevel(levelName);
        Mission mission = this.getOneByMission(level, missionName);
        List<Pair> pairList = this.missionService.select(course, mission);
        List<List<String>> pairOfNameList = new ArrayList<>();
        for (Pair pair : pairList) {
            List<String> nameList = pair.getCrewList().stream().map(Crew::getName).collect(Collectors.toList());
            pairOfNameList.add(nameList);
        }
        return pairOfNameList;
    }

    private Course getOneByCourse(String courseName) {
        return Course.findByName(courseName).get();
    }

    private Level getOneByLevel(String levelName) {
        return Level.findByName(levelName).get();
    }

    private Mission getOneByMission(Level level, String missionName) {
        return Mission.findByLevelAndName(level, missionName).get();
    }
}
