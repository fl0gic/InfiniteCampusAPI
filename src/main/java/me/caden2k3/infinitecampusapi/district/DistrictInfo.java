package me.caden2k3.infinitecampusapi.district;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class DistrictInfo {
    private int id;
    private Date dateCreated;
    private String districtAppName;
    private String districtBaseURL;
    private String districtCode;
    private String districtName;
    private int districtNumber;
    private String districtWebSite;
    private int mobileCheckins;
    private int portalCheckins;
    private String stateCode;
}
