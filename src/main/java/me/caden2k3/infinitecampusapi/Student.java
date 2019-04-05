package me.caden2k3.infinitecampusapi;

import lombok.Getter;
import lombok.Setter;
import me.caden2k3.infinitecampusapi.calendar.Calendar;
import me.caden2k3.infinitecampusapi.classbook.Classbook;
import me.caden2k3.infinitecampusapi.classbook.ClassbookManager;
import me.caden2k3.infinitecampusapi.classbook.GradingDetailSummary;
import me.caden2k3.infinitecampusapi.district.DistrictInfo;
import me.caden2k3.infinitecampusapi.exception.InvalidCredentialsException;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

@Getter @Setter public class Student {
    private InfiniteCampus core;
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
    private GradingDetailSummary gradeDetailSummary;

    private DistrictInfo distInfo;

    public Student(String username, String password, InfiniteCampus core) throws InvalidCredentialsException, IOException, ParsingException {
        this.core = core;
        distInfo = core.getDistrictInfo();

        //Ensure valid credentials.
        if (!core.attemptLogin(username, password))
            throw new InvalidCredentialsException();

        Builder builder = new Builder();

        URL infoURL = new URL(distInfo.getDistrictBaseURL() + "/prism?x=portal.PortalOutline&appName=" + distInfo.getDistrictAppName());
        Document doc = builder.build(new ByteArrayInputStream(core.getContent(infoURL, false).getBytes()));
        Element root = doc.getRootElement();
        Element studentElement = root
                .getFirstChildElement("PortalOutline")
                .getFirstChildElement("Family")
                .getFirstChildElement("Student");

        studentNumber = studentElement.getAttributeValue("studentNumber");
        personID = studentElement.getAttributeValue("personID");
        lastName = studentElement.getAttributeValue("lastName");
        firstName = studentElement.getAttributeValue("firstName");
        middleName = studentElement.getAttributeValue("middleName");
        isGuardian = studentElement.getAttributeValue("isGuardian");
        gradeDetailSummary = new GradingDetailSummary(studentElement.getFirstChildElement("GradingDetailSummary"));

        for (int i = 0; i < studentElement.getChildElements("Calendar").size(); i++)
            calendars.add(new Calendar(studentElement.getChildElements("Calendar").get(i)));
        for (int i = 0; i < studentElement.getChildElements("Classbook").size(); i++)
            classbooks.add(new Classbook(studentElement.getChildElements("Classbook").get(i)));

        primaryCalendar = calendars.get(0);
    }

    public Student(Element userElement) {
        this(userElement, null);
    }

    public Student(Element userElement, InfiniteCampus core) {
        this.core = core;
        if (core != null)
            distInfo = core.getDistrictInfo();

        studentNumber = userElement.getAttributeValue("studentNumber");
        personID = userElement.getAttributeValue("personID");
        lastName = userElement.getAttributeValue("lastName");
        firstName = userElement.getAttributeValue("firstName");
        middleName = userElement.getAttributeValue("middleName");
        isGuardian = userElement.getAttributeValue("isGuardian");
        gradeDetailSummary = new GradingDetailSummary(userElement.getFirstChildElement("GradingDetailSummary"));

        for (int i = 0; i < userElement.getChildElements("Calendar").size(); i++)
            calendars.add(new Calendar(userElement.getChildElements("Calendar").get(i)));
        for (int i = 0; i < userElement.getChildElements("Classbook").size(); i++)
            classbooks.add(new Classbook(userElement.getChildElements("Classbook").get(i)));

        if (calendars.size() > 0)
             primaryCalendar = calendars.get(0);
    }

    private String getPictureURL() {
        return distInfo.getDistrictBaseURL() + "personPicture.jsp?personID=" + personID;
    }

    public ClassbookManager getClassbookManager() throws ParsingException, IOException {
        Builder builder = new Builder();

        URL infoURL = new URL(distInfo.getDistrictBaseURL() +
                "/prism?&x=portal.PortalClassbook-getClassbookForAllSections" +
                "&mode=classbook" +
                "&personID=" + personID +
                "&structureID=" + calendars.get(0).getSchedules().get(0).getId() +
                "&calendarID=" + calendars.get(0).getCalendarID());

        Document doc2 = builder.build(new ByteArrayInputStream(core.getContent(infoURL, false).getBytes()));
        return new ClassbookManager(doc2.getRootElement().getFirstChildElement("SectionClassbooks"));
    }

    //TODO: Load news items
    public String getInfoString() {
        StringBuilder userInfo = new StringBuilder(
                "Information for " + firstName + " " + middleName + " " + lastName + ":" +
                "\nStudent Number: " + studentNumber +
                "\nPerson ID: " + personID +
                "\nPicture URL: " + getPictureURL() +
                "\nIs Guardian? " + isGuardian +
                "\n\n===Calendars===");

        for (Calendar c : calendars)
            userInfo.append("\n").append(c.getInfoString());

        return userInfo.toString();
    }
}
