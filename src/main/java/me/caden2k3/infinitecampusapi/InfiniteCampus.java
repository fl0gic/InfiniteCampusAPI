package me.caden2k3.infinitecampusapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import me.caden2k3.infinitecampusapi.district.DistrictInfo;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class InfiniteCampus {
    private String cookies = "";
    @Getter private DistrictInfo districtInfo;
    private String districtCode;

    public InfiniteCampus(String districtCode) {
        this.districtCode = districtCode;
        try {
            ObjectMapper mapper = new ObjectMapper();
            districtInfo = mapper.readValue(new URL("https://mobile.infinitecampus.com/mobile/checkDistrict?districtCode=" + districtCode), DistrictInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean attemptLogin(String user, String pass) {
        try {
            URL loginURL = new URL(districtInfo.getDistrictBaseURL() + "/verify.jsp?nonBrowser=true&username=" + user + "&password=" + pass + "&appName=" + districtInfo.getDistrictAppName());
            String response = getContent(loginURL, true);
            if (response.trim().equalsIgnoreCase("<authentication>success</authentication>"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getContent(URL url, boolean alterCookies) {
        StringBuilder s = new StringBuilder();
        try {
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestProperty("Cookie", cookies); //Retain our session
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String input;
            while ((input = br.readLine()) != null) {
                s.append(input).append("\n");
            }
            br.close();

            StringBuilder sb = new StringBuilder();

            // find the cookies in the response header from the first request
            List<String> cookieList = con.getHeaderFields().get("Set-Cookie");
            if (cookieList != null) {
                for (String currentCookie : cookieList) {
                    if (sb.length() > 0) {
                        sb.append("; ");
                    }

                    // only want the first part of the cookie header that has the value
                    String value = currentCookie.split(";")[0];
                    sb.append(value);
                }
            }
            if (alterCookies)
                cookies = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s.toString();
    }
}
