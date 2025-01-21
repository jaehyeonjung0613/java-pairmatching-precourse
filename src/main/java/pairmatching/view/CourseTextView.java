package pairmatching.view;

import static pairmatching.view.CourseTextViewConstants.*;

import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.ui.OutputHelper;

public class CourseTextView extends TextView {
    @Override
    void show(OutputHelper outputHelper) {
        List<String> nameList = Course.findAll().stream().map(Course::getName).collect(Collectors.toList());
        outputHelper.println(String.format(COURSE_NAME_LIST_OUTPUT_FORMAT, String.join(COURSE_NAME_SEPARATOR, nameList)));
    }
}
