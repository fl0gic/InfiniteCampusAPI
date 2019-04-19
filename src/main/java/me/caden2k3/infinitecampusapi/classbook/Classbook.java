package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class Classbook {
    private String termName;
    private String courseNumber;
    private String courseName;
    private String sectionNumber;
    private String teacherDisplay;

    private ArrayList<ClassbookTask> tasks = new ArrayList<>();

    /**
     * @return The weighted average of all tasks in the class.
     */
    public double getGradePercentage() {
        //map<grade, weight>
        HashMap<Float, Float> classGrades = new HashMap<>();
        tasks.forEach(task -> classGrades.put(task.getPercentage(), task.getWeight()));

        double num = 0;
        double denom = 0;
        for (Map.Entry<Float, Float> entry : classGrades.entrySet()) {
            num += entry.getKey() * entry.getValue();
            denom += entry.getValue();
        }

        return num / denom;
    }

    public String getInfoString() {
        StringBuilder str = new StringBuilder("\nTasks for " + courseName + ", with teacher " + teacherDisplay + " and class ID " + courseNumber + ", " + termName);

        for (ClassbookTask t : tasks)
            str.append("\n").append(t.getInfoString());
        return str.toString();
    }
}
