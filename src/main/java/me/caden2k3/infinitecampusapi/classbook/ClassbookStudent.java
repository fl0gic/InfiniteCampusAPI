package me.caden2k3.infinitecampusapi.classbook;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Caden Kriese (flogic)
 *
 * Created on 2019-04-20
 */
@Getter @Setter
public class ClassbookStudent {
    private String firstName;
    private String lastName;
    private String middleName;

    private int personID;
    private int studentNumber;
    private String personGUID;

    private String gender;
    private String birthCountry;
    private String serviceType;
    private String startYear;
    private String endYear;
    private String label;
    private String homePrimaryLanguage;
    private String calendarName;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date startDate;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date calendarStart;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date calendarEnd;
    private int startStatus;
    private int raceEthnicity;
    private int enrollmentID;
    private int grade;
    private int calendarID;
    private int structureID;
    private int schoolID;
    private int districtID;

    private boolean noShow;
    private boolean activeYear;
    private boolean attendanceTakenPriorSection;
    private boolean hasStrictConstraints;
    private boolean stateUseFlag;
    private boolean creditRecovery;
    private boolean ageInMonthsAtFirstDistrictEnrollment;
    private boolean ageInMonthsAtDateEnteredState;
    private boolean isAutoStateIDOn;
    private boolean assignedANewID;
    private boolean enableHistoryThisInstance;


}
