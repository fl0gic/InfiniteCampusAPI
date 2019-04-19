package me.caden2k3.infinitecampusapi.calendar;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
