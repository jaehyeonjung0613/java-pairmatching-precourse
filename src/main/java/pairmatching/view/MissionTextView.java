package pairmatching.view;

import static pairmatching.view.MissionTextViewConstants.*;

import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.ui.OutputHelper;

public class MissionTextView extends TextView {
    @Override
    void show(OutputHelper outputHelper) {
        List<Level> levelList = Level.findAll();
        outputHelper.println(MISSION_OUTPUT_LOGO);
        for (Level level : levelList) {
            List<String> missionNameList = Mission.findAllByLevel(level)
                .stream()
                .map(Mission::getName)
                .collect(Collectors.toList());
            outputHelper.println(String.format(MISSION_NAME_LIST_OUTPUT_FORMAT, level.getName(),
                String.join(MISSION_NAME_SEPARATOR, missionNameList)));
        }
    }
}
