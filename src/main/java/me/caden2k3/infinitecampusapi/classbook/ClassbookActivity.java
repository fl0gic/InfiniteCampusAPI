package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;
import nu.xom.Element;

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

    ClassbookActivity(Element activity) {
        activityID = activity.getAttributeValue("activityID");
        name = activity.getAttributeValue("name");
        abbrev = activity.getAttributeValue("abbrev");
        dueDate = activity.getAttributeValue("dueDate");
        assignedDate = activity.getAttributeValue("assignedDate");
        totalPoints = Float.parseFloat(activity.getAttributeValue("totalPoints"));
        active = activity.getAttributeValue("active").equalsIgnoreCase("true");
        notGraded = activity.getAttributeValue("notGraded").equalsIgnoreCase("true");
        weight = Float.parseFloat(activity.getAttributeValue("weight"));
        scoringType = activity.getAttributeValue("scoringType");
        validScore = activity.getAttributeValue("validScore").equalsIgnoreCase("validScore");
        scoreID = activity.getAttributeValue("scoreID");
        score = Float.parseFloat(activity.getAttributeValue("score"));
        late = activity.getAttributeValue("late").equalsIgnoreCase("true");
        missing = activity.getAttributeValue("missing").equalsIgnoreCase("true");
        incomplete = activity.getAttributeValue("incomplete").equalsIgnoreCase("true");
        turnedIn = activity.getAttributeValue("turnedIn").equalsIgnoreCase("true");
        cheated = activity.getAttributeValue("cheated").equalsIgnoreCase("true");
        dropped = activity.getAttributeValue("dropped").equalsIgnoreCase("true");
        percentage = Float.parseFloat(activity.getAttributeValue("percentage"));
        letterGrade = activity.getAttributeValue("letterGrade");
        weightedScore = Float.parseFloat(activity.getAttributeValue("weightedScore"));
        weightedTotalPoints = Float.parseFloat(activity.getAttributeValue("weightedTotalPoints"));
        weightedPercentage = Float.parseFloat(activity.getAttributeValue("weightedPercentage"));
        numericScore = Integer.parseInt(activity.getAttributeValue("numericScore"));
        wysiwygSubmission = activity.getAttributeValue("wysiwygSubmission").equalsIgnoreCase("true");
        onlineAssessment = activity.getAttributeValue("onlineAssessment").equalsIgnoreCase("true");
    }

    public String getInfoString() {
        return name + "\t\t" + letterGrade + " " + weightedPercentage + "%" + "\t(" + score + "/" + (int) totalPoints + ")";
    }
}
