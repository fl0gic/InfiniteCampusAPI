package me.caden2k3.infinitecampusapi.classbook;

import me.caden2k3.infinitecampusapi.Student;
import nu.xom.Element;
import nu.xom.Elements;

import java.util.ArrayList;

public class PortalClassbook {
    public String sectionID;
    public ArrayList<Student> students = new ArrayList<Student>();

    public PortalClassbook(Element classbookElement) {
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
