package pairmatching.ui;

import static pairmatching.ui.OutputHelperConstants.*;

public abstract class OutputHelper implements Output {
    public final void printNextLine() {
        this.println("");
    }

    public final void printError(String message) {
        this.println(String.format(ERROR_OUTPUT_FORMAT, message));
    }
}
