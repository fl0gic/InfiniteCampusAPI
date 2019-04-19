package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ClassbookGroup {
    private String activityID;
    private String name;
    private float weight;
    private int seq;
    private boolean notGraded = false;
    //hidePortal
    //hasValidScore
    //composite
    //calcExclude
    private int termID;
    private int taskID;
    private float percentage;
    private String formattedPercentage;
    private String letterGrade;
    private float pointsEarned;
    private float totalPointsPossible;
    private ArrayList<ClassbookActivity> activities;

    public String getInfoString() {
        String str = name + (name.length() < 16 ? "\t" : "") + "\t(" + (weight > 0 ? "Weight: " + weight + ", " : "") + "Grade: " + letterGrade + ", " + formattedPercentage + "%)";

        for (ClassbookActivity a : activities)
            str += "\n\t" + a.getInfoString().replace("\n", "\n\t");

        return str;
    }
}
