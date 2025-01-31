package pairmatching.controller.view;

import java.util.concurrent.atomic.AtomicBoolean;

import pairmatching.controller.MissionController;
import pairmatching.exception.IllegalArgumentBeanException;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.CourseTextView;
import pairmatching.view.MissionTextView;
import pairmatching.view.View;
import pairmatching.view.component.MissionInputFormView;
import pairmatching.view.component.ReMatchingSelectFormView;
import pairmatching.view.component.ReMatchingSelectItem;
import pairmatching.view.component.SelectHandler;
import pairmatching.view.layout.RepeatView;
import pairmatching.view.layout.SerializeView;import pairmatching.view.layout.SharpFrameView;

public class PairMatchingViewController implements ViewController {
    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;
    // View Controller
    private final PairSelectionViewController pairSelectionViewController;
    // Controller
    private final MissionController missionController = new MissionController();

    public PairMatchingViewController(InputHelper inputHelper, OutputHelper outputHelper) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
        // View Controller
        this.pairSelectionViewController = new PairSelectionViewController(outputHelper);
    }

    @Override
    public View make() {
        return new SerializeView(
            new SharpFrameView(new SerializeView(new CourseTextView(), new MissionTextView())),
            new RepeatView(new MissionInputFormView(this::match)));
    }

    public void match(String command) {
        if (this.missionController.checkPairMatched(command) && !this.queryReMatching()) {
            throw new IllegalArgumentBeanException();
        }
        this.missionController.match(command);
        this.pairSelectionViewController.select(command);
    }

    private boolean queryReMatching() {
        AtomicBoolean result = new AtomicBoolean(false);
        View view = new RepeatView(new ReMatchingSelectFormView(SelectHandler.builder()
            .addEventListener(ReMatchingSelectItem.YES, () -> result.set(true))
            .addEventListener(ReMatchingSelectItem.NO, () -> result.set(false))));
        view.execute(this.inputHelper, this.outputHelper);
        return result.get();
    }
}
