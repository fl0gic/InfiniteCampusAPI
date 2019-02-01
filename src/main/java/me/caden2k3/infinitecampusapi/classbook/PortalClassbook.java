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
    private ArrayList<Student> students = new ArrayList<Student>();

    PortalClassbook(Element classbookElement) {
        this.sectionID = classbookElement.getAttributeValue("sectionID");
        Elements e = classbookElement.getFirstChildElement("ClassbookDetail").getFirstChildElement("StudentList").getChildElements("Student");
        for (int i = 0; i < e.size(); i++)
            students.add(new Student(e.get(i)));
    }

    public String getInfoString() {
        String str = "";
        for (Student s : students)
            for (Classbook c : s.classbooks) {
                str += c.getInfoString();
            }
        return str;
    }
}
