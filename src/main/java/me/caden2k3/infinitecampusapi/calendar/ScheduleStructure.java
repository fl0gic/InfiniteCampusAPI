package me.caden2k3.infinitecampusapi.calendar;

import lombok.Getter;
import lombok.Setter;
import nu.xom.Element;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Getter @Setter
public class ScheduleStructure {
    private String id;
    private String name;
    private String label;
    private String grade;
    private boolean active;
    private String primary;
    private boolean is_default;
    private Date startDate;

    ScheduleStructure(Element scheduleElement) {
        id = scheduleElement.getAttributeValue("structureID");
        name = scheduleElement.getAttributeValue("structureName");
        label = scheduleElement.getAttributeValue("label");
        grade = scheduleElement.getAttributeValue("grade");
        active = scheduleElement.getAttributeValue("active").equalsIgnoreCase("true");
        primary = scheduleElement.getAttributeValue("primary");
        is_default = scheduleElement.getAttributeValue("default").equalsIgnoreCase("true");
        try {
            startDate = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH).parse(scheduleElement.getAttributeValue("startDate"));
        } catch (Exception e) {
            startDate = new Date();
        }
    }

    public String getInfoString() {
        return "Information for Schedule \'" + name + "\' titled \'" + label + "\':" +
                "\nGrade: " + grade +
                "\nID: " + id +
                "\nIs Active? " + active +
                "\nPrimary: " + primary +
                "\nIs Default? " + is_default +
                "\nEnding Date: " + startDate.toString();
    }
}
