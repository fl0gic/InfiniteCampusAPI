package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ClassbookTask {
    private String taskID;
    private String name;
    private float weight;
    private boolean isWeighted = false;
    // hasValidGroup
    // hasValidWeightedGroup
    // locked
    private boolean gradeBookPosted = false;
    private int taskSeq;
    private int termID;
    private String termName;
    private int termSeq;
    private float totalPointsPossible;
    private float pointsEarned;
    private float percentage;
    private String letterGrade;
    private String formattedPercentage;
    private int curveID;
    private ArrayList<ClassbookGroup> groups = new ArrayList<>();

    public String getInfoString() {
        StringBuilder str = new StringBuilder("Task: " + name + ", " + termName + " " + letterGrade + " " + formattedPercentage + "%");

        for (ClassbookGroup b : groups)
            str.append("\n\t").append(b.getInfoString().replace("\n", "\n\t"));

        return str.toString();
    }
}
