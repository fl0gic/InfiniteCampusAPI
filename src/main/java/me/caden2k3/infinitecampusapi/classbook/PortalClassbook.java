package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;
import me.caden2k3.infinitecampusapi.Student;
import nu.xom.Element;
import nu.xom.Elements;

import java.util.ArrayList;

@Getter @Setter
public class PortalClassbook {
    private String sectionID;
    private ArrayList<Student> students = new ArrayList<>();

    PortalClassbook(Element classbookElement) {
        this.sectionID = classbookElement.getAttributeValue("sectionID");
        Elements elements = classbookElement.getFirstChildElement("ClassbookDetail").getFirstChildElement("StudentList").getChildElements("Student");
        for (int i = 0; i < elements.size(); i++)
            students.add(new Student(elements.get(i)));
    }

    public String getInfoString() {
        StringBuilder infoString = new StringBuilder();

        for (Student student : students) {
            for (Classbook classbook : student.getClassbooks())
                infoString.append(classbook.getInfoString());
        }
        return infoString.toString();
    }
}
