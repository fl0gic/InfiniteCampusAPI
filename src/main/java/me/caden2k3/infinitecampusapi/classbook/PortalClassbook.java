package me.caden2k3.infinitecampusapi.classbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import me.caden2k3.infinitecampusapi.Student;

import java.util.ArrayList;

@Getter @Setter
public class PortalClassbook {
    private String sectionID;
    @JsonProperty("StudentList")
    private ArrayList<ClassbookStudent> studentList = new ArrayList<>();

    public String getInfoString() {
//        StringBuilder infoString = new StringBuilder();
//
//        for (Student student : students) {
//            for (Classbook classbook : student.getClassbooks())
//                infoString.append(classbook.getInfoString());
//        }
//        return infoString.toString();
        return "";
    }
}
