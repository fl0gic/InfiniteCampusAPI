package me.caden2k3.infinitecampusapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import me.caden2k3.infinitecampusapi.district.DistrictInfo;
import me.caden2k3.infinitecampusapi.exception.InvalidCredentialsException;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    public Student getStudent(String username, String password) throws ParsingException, IOException, InvalidCredentialsException {
        //Ensure valid credentials.
        if (!checkCredentials(username, password))
            throw new InvalidCredentialsException();

        Builder builder = new Builder();

        URL infoURL = new URL(districtInfo.getDistrictBaseURL() + "/prism?x=portal.PortalOutline&appName=" + districtInfo.getDistrictAppName());
        Document doc = builder.build(new ByteArrayInputStream(getContent(infoURL, false).getBytes()));
        Element root = doc.getRootElement();
        return new Student(root
                .getFirstChildElement("PortalOutline")
                .getFirstChildElement("Family")
                .getFirstChildElement("Student"));
    }

    public boolean checkCredentials(String username, String password) {
        try {
            URL loginURL = new URL(districtInfo.getDistrictBaseURL() + "/verify.jsp?nonBrowser=true&username=" + username + "&password=" + password + "&appName=" + districtInfo.getDistrictAppName());
            String response = getContent(loginURL, true);
            if (response.trim().equalsIgnoreCase("<authentication>success</authentication>"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getContent(URL url, boolean alterCookies) throws IOException {
        StringBuilder builder = new StringBuilder();

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Cookie", cookies); //Retain our session
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String input;
        while ((input = reader.readLine()) != null) {
            builder.append(input).append("\n");
        }
        reader.close();

        StringBuilder builder2 = new StringBuilder();
        // find the cookies in the response header from the first request
        List<String> cookieList = connection.getHeaderFields().get("Set-Cookie");
        if (cookieList != null) {
            for (String currentCookie : cookieList) {
                if (builder2.length() > 0) {
                    builder2.append("; ");
                }

                // only want the first part of the cookie header that has the value
                String value = currentCookie.split(";")[0];
                builder2.append(value);
            }
        }

        if (alterCookies)
            cookies = builder2.toString();
        return builder.toString();
    }
}
