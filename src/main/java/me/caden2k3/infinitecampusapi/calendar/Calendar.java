package me.caden2k3.infinitecampusapi.calendar;

import lombok.Getter;
import lombok.Setter;
import nu.xom.Element;

import java.util.ArrayList;

@Getter @Setter
public class Calendar {
    private String name;
    private String schoolID;
    public String calendarID;
    private String endYear;

    public ArrayList<ScheduleStructure> schedules = new ArrayList<>();

    public Calendar(Element calendar) {
        name = calendar.getAttributeValue("calendarName");
        schoolID = calendar.getAttributeValue("schoolID");
        calendarID = calendar.getAttributeValue("calendarID");
        endYear = calendar.getAttributeValue("endYear");
        for (int i = 0; i < calendar.getChildElements().size(); i++)
            schedules.add(new ScheduleStructure(calendar.getChildElements().get(i)));
    }

    public String getInfoString() {
        StringBuilder builder = new StringBuilder(
                "Information for Calendar \'" + name + "\':" +
                        "\nSchool ID: " + schoolID +
                        "\nCalendar ID: " + calendarID +
                        "\nEnding Year: " + endYear +
                        "\n\n===Schedules===");

        for (ScheduleStructure structure : schedules)
            builder.append("\n").append(structure.getInfoString());

        return builder.toString();
    }
}
