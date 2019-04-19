package me.caden2k3.infinitecampusapi;

import lombok.Getter;
import lombok.Setter;
import me.caden2k3.infinitecampusapi.calendar.Calendar;
import me.caden2k3.infinitecampusapi.classbook.Classbook;

import java.util.ArrayList;

@Getter @Setter
public class Student {
    private InfiniteCampusAPI core;
    private String studentNumber;
    private boolean hasSecurityRole = false;
    private String personID;
    private String lastName;
    private String firstName;
    private String middleName;
    private String isGuardian;
    private Calendar primaryCalendar;

    private ArrayList<Calendar> calendars = new ArrayList<>();
    private ArrayList<Classbook> classbooks = new ArrayList<>();

    public String getInfoString() {
        StringBuilder userInfo = new StringBuilder(
                "Information for " + firstName + " " + middleName + " " + lastName + ":" +
                        "\nStudent Number: " + studentNumber +
                        "\nPerson ID: " + personID +
                        "\nIs Guardian? " + isGuardian +
                        "\n\n===Calendars===");

        for (Calendar c : calendars)
            userInfo.append("\n").append(c.getInfoString());

        return userInfo.toString();
    }
}
