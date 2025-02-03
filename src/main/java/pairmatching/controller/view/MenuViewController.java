package pairmatching.controller.view;

import pairmatching.controller.MissionController;
import pairmatching.ui.InputHelper;
import pairmatching.ui.OutputHelper;
import pairmatching.view.View;
import pairmatching.view.component.MenuSelectFormView;
import pairmatching.view.component.MenuSelectItem;
import pairmatching.view.component.SelectHandler;

public class MenuViewController implements ViewController {
    private final InputHelper inputHelper;
    private final OutputHelper outputHelper;
    private final Runnable endHandler;
    // ViewController
    private final PairMatchingViewController pairMatchingViewController;
    private final PairSelectionViewController pairSelectionViewController;
    // Controller
    private final MissionController missionController;

    public MenuViewController(InputHelper inputHelper, OutputHelper outputHelper, Runnable endHandler) {
        this.inputHelper = inputHelper;
        this.outputHelper = outputHelper;
        this.endHandler = endHandler;
        // ViewController
        this.pairMatchingViewController = new PairMatchingViewController(inputHelper, outputHelper);
        this.pairSelectionViewController = new PairSelectionViewController(outputHelper);
        // Controller
        this.missionController = new MissionController();
    }

    @Override
    public View make() {
        return new MenuSelectFormView(SelectHandler.builder()
            .addEventListener(MenuSelectItem.PAIR_MATCHING, this::openPairMatching)
            .addEventListener(MenuSelectItem.PAIR_SELECTION, this::openPairSelection)
            .addEventListener(MenuSelectItem.PAIR_RESET, this::initPair)
            .addEventListener(MenuSelectItem.END, this.endHandler));
    }

    public void openPairMatching() {
        View pairMatchingView = pairMatchingViewController.make();
        pairMatchingView.execute(this.inputHelper, this.outputHelper);
    }

    public void openPairSelection() {
        View pairSelectionView = pairSelectionViewController.make();
        pairSelectionView.execute(inputHelper, outputHelper);
    }

    public void initPair() {
        this.missionController.init();
        this.outputHelper.println("초기화 되었습니다.");
        this.outputHelper.printNextLine();
    }
}
