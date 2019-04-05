package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;
import nu.xom.Element;

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

    private ArrayList<ClassbookTask> tasks = new ArrayList<>();
    private ArrayList<ClassbookGroup> groups = new ArrayList<>();

    ClassbookTask(Element task) {
        name = task.getAttributeValue("name");
        taskID = task.getAttributeValue("taskID");
        weight = Float.parseFloat(task.getAttributeValue("weight"));
        isWeighted = task.getAttributeValue("isWeighted").equalsIgnoreCase("true");
        gradeBookPosted = task.getAttributeValue("gradeBookPosted").equalsIgnoreCase("true");
        taskSeq = Integer.parseInt(task.getAttributeValue("taskSeq"));
        termID = Integer.parseInt(task.getAttributeValue("termID"));
        termName = task.getAttributeValue("termName");
        termSeq = Integer.parseInt(task.getAttributeValue("termSeq"));
        totalPointsPossible = Float.parseFloat(task.getAttributeValue("totalPointsPossible"));
        pointsEarned = Float.parseFloat(task.getAttributeValue("pointsEarned"));
        percentage = Float.parseFloat(task.getAttributeValue("percentage"));
        letterGrade = task.getAttributeValue("letterGrade");
        formattedPercentage = task.getAttributeValue("formattedPercentage");

        try {
            for (int i = 0; i < task.getFirstChildElement("groups").getChildElements("ClassbookGroup").size(); i++)
                groups.add(new ClassbookGroup(task.getFirstChildElement("groups").getChildElements("ClassbookGroup").get(i)));
        } catch (Exception ignored) { }

        try {
            curveID = Integer.parseInt(task.getAttributeValue("curveID"));

            for (int i = 0; i < task.getFirstChildElement("tasks").getChildElements("ClassbookTask").size(); i++)
                tasks.add(new ClassbookTask(task.getFirstChildElement("tasks").getChildElements("ClassbookTask").get(i)));
        } catch (Exception ignored) { }

        letterGrade = (letterGrade == null ? "?" : letterGrade);
        formattedPercentage = (formattedPercentage == null ? "?" : formattedPercentage);
    }

    public String getInfoString() {
        StringBuilder str = new StringBuilder("Task: " + name + ", " + termName + " " + letterGrade + " " + formattedPercentage + "%");
        for (ClassbookTask t : tasks)
            str.append("\n\t").append(t.getInfoString().replace("\n", "\n\t"));
        for (ClassbookGroup b : groups)
            str.append("\n\t").append(b.getInfoString().replace("\n", "\n\t"));
        return str.toString();
    }
}
