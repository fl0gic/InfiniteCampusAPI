package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;
import nu.xom.Element;

import java.util.ArrayList;

@Getter @Setter
public class Classbook {
    private String termName;
    private String courseNumber;
    private String courseName;
    private String sectionNumber;
    private String teacherDisplay;

    private ArrayList<ClassbookTask> tasks = new ArrayList<>();

    public Classbook(Element classbook) {
        termName = classbook.getAttributeValue("termName");
        courseNumber = classbook.getAttributeValue("courseNumber");
        courseName = classbook.getAttributeValue("courseName");
        sectionNumber = classbook.getAttributeValue("sectionNumber");
        teacherDisplay = classbook.getAttributeValue("teacherDisplay");

        for (int i = 0; i < classbook.getFirstChildElement("tasks").getChildElements("ClassbookTask").size(); i++)
            tasks.add(new ClassbookTask(classbook.getFirstChildElement("tasks").getChildElements("ClassbookTask").get(i)));
    }

    public String getInfoString() {
        StringBuilder str = new StringBuilder("\nTasks for " + courseName + ", with teacher " + teacherDisplay + " and class ID " + courseNumber + ", " + termName);

        for (ClassbookTask t : tasks)
            str.append("\n").append(t.getInfoString());
        return str.toString();
    }
}
