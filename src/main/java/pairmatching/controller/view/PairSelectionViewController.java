package pairmatching.controller.view;

import static pairmatching.controller.view.PairSelectionViewControllerConstants.*;

import java.util.List;

import pairmatching.controller.MissionController;
import pairmatching.ui.OutputHelper;
import pairmatching.view.CourseTextView;
import pairmatching.view.MissionTextView;
import pairmatching.view.View;
import pairmatching.view.component.MissionInputFormView;
import pairmatching.view.layout.RepeatView;
import pairmatching.view.layout.SerializeView;
import pairmatching.view.layout.SharpFrameView;

public class PairSelectionViewController implements ViewController {
    private final OutputHelper outputHelper;
    // Controller
    private final MissionController missionController = new MissionController();

    public PairSelectionViewController(OutputHelper outputHelper) {
        this.outputHelper = outputHelper;
    }

    @Override
    public View make() {
        return new SerializeView(new SharpFrameView(
            new SerializeView(new CourseTextView(), new MissionTextView())),
            new RepeatView(new MissionInputFormView(this::select)));
    }

    public void select(String command) {
        List<List<String>> pairOfNameList = this.missionController.select(command);
        this.outputHelper.println("페어 매칭 결과입니다.");
        for (List<String> pairOfName : pairOfNameList) {
            this.outputHelper.println(String.format(String.join(PAIR_IN_CREW_SEPARATOR, pairOfName)));
        }
        this.outputHelper.printNextLine();
    }
}
