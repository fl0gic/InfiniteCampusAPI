package me.caden2k3.infinitecampusapi.district;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class DistrictInfo {
    private String districtAppName;
    private String districtBaseURL;
    private String districtName;
    private int districtNumber;
    private String stateCode;
}
