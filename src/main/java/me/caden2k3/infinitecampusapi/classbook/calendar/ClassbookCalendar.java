package me.caden2k3.infinitecampusapi.classbook.calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import me.caden2k3.infinitecampusapi.calendar.ScheduleStructure;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Caden Kriese (flogic)
 *
 * Created on 2019-04-20
 */
@Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true)
public class ClassbookCalendar {
    private int districtID;
    private int calendarID;
    private int schoolID;
    private String name;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date startDate;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date endDate;
    private String comments;
    private boolean exclude;
    private boolean sifExclude;
    private boolean showSIFExclude;
    private boolean summerSchool;
    private boolean schoolChoice;
    private int studentDay;
    private int teacherDay;
    private int wholeDayAbsence;
    private int halfDayAbsence;
    private boolean title3;
    private boolean assignmentRequired;
    private boolean positiveAttendanceEnabled;
    private int positiveAttendanceEditDays;
    private boolean externalLMSExclude;
    private boolean echs;
    private boolean stem;
    private int daysInSessionOK;
    private int minutesPerDayOK;
    private int totalInstructionalMinutesOK;

    @JsonProperty("SchoolYear")
    private SchoolYear schoolYear;
    private ArrayList<ScheduleStructure> scheduleStructures;
}
