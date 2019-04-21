package me.caden2k3.infinitecampusapi.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Calendar {
    private String calendarName;
    private String schoolID;
    private String calendarID;
    private String endYear;
    @JsonProperty("ScheduleStructure")
    private ScheduleStructure scheduleStructure;

    public String getInfoString() {
        return "Information for Calendar \'" + calendarName + "\':" +
                                 "\nSchool ID: " + schoolID +
                                 "\nCalendar ID: " + calendarID +
                                 "\nEnding Year: " + endYear +
                                 "\n\n===Schedules===" + "\n" + scheduleStructure.getInfoString();
    }
}
