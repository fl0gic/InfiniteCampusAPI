package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;
import nu.xom.Element;

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

    private ArrayList<ClassbookActivity> activities = new ArrayList<ClassbookActivity>();

    ClassbookGroup(Element group) {
        activityID = group.getAttributeValue("activityID");
        name = group.getAttributeValue("name");
        weight = Float.parseFloat(group.getAttributeValue("weight"));
        seq = Integer.parseInt(group.getAttributeValue("seq"));
        notGraded = group.getAttributeValue("notGraded").equalsIgnoreCase("true");
        termID = Integer.parseInt(group.getAttributeValue("termID"));
        taskID = Integer.parseInt(group.getAttributeValue("taskID"));
        percentage = Float.parseFloat(group.getAttributeValue("percentage"));
        formattedPercentage = group.getAttributeValue("formattedPercentage");
        letterGrade = group.getAttributeValue("letterGrade");
        pointsEarned = Float.parseFloat(group.getAttributeValue("pointsEarned"));
        totalPointsPossible = Float.parseFloat(group.getAttributeValue("totalPointsPossible"));

        for (int i = 0; i < group.getFirstChildElement("activities").getChildElements("ClassbookActivity").size(); i++)
            activities.add(new ClassbookActivity(group.getFirstChildElement("activities").getChildElements("ClassbookActivity").get(i)));


        letterGrade = (letterGrade == null ? "?" : letterGrade);
        formattedPercentage = (formattedPercentage == null ? "?" : formattedPercentage);
    }

    public String getInfoString() {
        String str = name + (name.length() < 16 ? "\t" : "") + "\t(" + (weight > 0 ? "Weight: " + weight + ", " : "") + "Grade: " + letterGrade + ", " + formattedPercentage + "%)";

        for (ClassbookActivity a : activities)
            str += "\n\t" + a.getInfoString().replace("\n", "\n\t");

        return str;
    }
}
