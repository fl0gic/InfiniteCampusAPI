package me.caden2k3.infinitecampusapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import me.caden2k3.infinitecampusapi.calendar.Calendar;
import me.caden2k3.infinitecampusapi.calendar.FutureCalendar;

@Getter @Setter
public class Student {
    private String studentNumber;
    private boolean hasSecurityRole;
    private String personID;
    private String lastName;
    private String firstName;
    private String middleName;
    private String isGuardian;
    @JsonProperty("FutureCalendar")
    private FutureCalendar futureCalendar;
    @JsonProperty("Calendar")
    private Calendar calendar;
    @JsonProperty("CustomTab")
    private CustomTab customTab;

    public String getInfoString() {
        return "Information for " + firstName + " " + middleName + " " + lastName + ":" +
                                  "\nStudent Number: " + studentNumber +
                                  "\nPerson ID: " + personID +
                                  "\nIs Guardian? " + isGuardian +
                                  "\n\n===Calendars===" + "\n" + calendar.getInfoString();
    }
}
