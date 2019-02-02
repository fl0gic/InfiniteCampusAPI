package me.caden2k3.infinitecampusapi;

import lombok.Getter;
import lombok.Setter;
import me.caden2k3.infinitecampusapi.calendar.Calendar;
import me.caden2k3.infinitecampusapi.classbook.Classbook;
import me.caden2k3.infinitecampusapi.classbook.GradingDetailSummary;
import me.caden2k3.infinitecampusapi.district.DistrictInfo;
import nu.xom.Element;

import java.util.ArrayList;

@Getter @Setter
public class Student {
    private String studentNumber;
    private boolean hasSecurityRole = false;
    private String personID;
    private String lastName;
    private String firstName;
    private String middleName;
    private String isGuardian;

    private ArrayList<Calendar> calendars = new ArrayList<>();
    private ArrayList<Classbook> classbooks = new ArrayList<>();
    private GradingDetailSummary gradeDetailSummary;

    private DistrictInfo distInfo;

    public Student(Element userElement) {
        this(userElement, null);
    }

    public Student(Element userElement, DistrictInfo info) {
        distInfo = info;

        studentNumber = userElement.getAttributeValue("studentNumber");
        personID = userElement.getAttributeValue("personID");
        lastName = userElement.getAttributeValue("lastName");
        firstName = userElement.getAttributeValue("firstName");
        middleName = userElement.getAttributeValue("middleName");
        isGuardian = userElement.getAttributeValue("isGuardian");
        for (int i = 0; i < userElement.getChildElements("Calendar").size(); i++)
            calendars.add(new Calendar(userElement.getChildElements("Calendar").get(i)));
        gradeDetailSummary = new GradingDetailSummary(userElement.getFirstChildElement("GradingDetailSummary"));
        for (int i = 0; i < userElement.getChildElements("Classbook").size(); i++)
            classbooks.add(new Classbook(userElement.getChildElements("Classbook").get(i)));
    }

    private String getPictureURL() {
        return distInfo.getDistrictBaseURL() + "personPicture.jsp?personID=" + personID;
    }

    //TODO: Load news items
    public String getInfoString() {
        StringBuilder userInfo = new StringBuilder("Information for " + firstName + " " + middleName + " " + lastName + ":\nStudent Number: " + studentNumber + "\nPerson ID: " + personID + "\nPicture URL: " + getPictureURL() + "\nIs Guardian? " + isGuardian + "\n\n===Calendars===");

        for (Calendar c : calendars)
            userInfo.append("\n").append(c.getInfoString());

        return userInfo.toString();
    }
}
