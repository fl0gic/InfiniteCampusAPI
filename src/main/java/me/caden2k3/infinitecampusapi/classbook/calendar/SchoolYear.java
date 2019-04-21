package me.caden2k3.infinitecampusapi.classbook.calendar;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Caden Kriese (flogic)
 *
 * Created on 2019-04-20
 */
@Getter @Setter
public class SchoolYear {
    private String startYear;
    private String endYear;
    private String label;
    private boolean active;
}
