package me.caden2k3.infinitecampusapi.classbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true)
public class Classbook {
    private String termName;
    private String courseName;
    @JsonProperty("teacherDisplay")
    private String teacherName;
    private int courseNumber;
    private int courseID;
    private int sectionNumber;
    private int sectionID;

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
}
