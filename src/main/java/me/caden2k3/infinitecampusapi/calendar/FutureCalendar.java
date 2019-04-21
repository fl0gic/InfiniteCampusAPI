package me.caden2k3.infinitecampusapi.calendar;

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
public class FutureCalendar {
    private String primary;
    private String calendarName;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date startDate;
    private int grade;
    private int schoolID;
    private int calendarID;
    private int structureID;
    private int endYear;
}
