package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassbookActivity {
    private String activityID;
    private String name;
    private String abbrev;
    private String dueDate;
    private String assignedDate;
    private float totalPoints;
    private boolean active;
    private boolean notGraded;
    //hidePortal
    //seq
    private float weight;
    private String scoringType;
    private boolean validScore;
    private String scoreID;
    private float score;
    private boolean late;
    private boolean missing;
    private boolean incomplete;
    private boolean turnedIn;
    public boolean exempt;
    private boolean cheated;
    private boolean dropped;
    private float percentage;
    private String letterGrade;
    private float weightedScore;
    private float weightedTotalPoints;
    private float weightedPercentage;
    private int numericScore;
    private boolean wysiwygSubmission;
    private boolean onlineAssessment;

    public String getInfoString() {
        return name + "\t\t" + letterGrade + " " + weightedPercentage + "%" + "\t(" + score + "/" + (int) totalPoints + ")";
    }
}
