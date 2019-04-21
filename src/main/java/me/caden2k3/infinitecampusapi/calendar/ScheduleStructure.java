package me.caden2k3.infinitecampusapi.calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleStructure {
    private String structureID;
    private String structureName;
    private String label;
    private String grade;
    private String calendarName;
    private int schoolID;
    private int calendarID;
    private boolean active;
    private String primary;
    @JsonProperty("default")
    private boolean isDefault;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date startDate;

    public String getInfoString() {
        return "Information for Schedule \'" + structureName + "\' titled \'" + label + "\':" +
                       "\nGrade: " + grade +
                       "\nID: " + structureID +
                       "\nIs Active? " + active +
                       "\nPrimary: " + primary +
                       "\nIs Default? " + isDefault +
                       "\nEnding Date: " + startDate;
    }
}
