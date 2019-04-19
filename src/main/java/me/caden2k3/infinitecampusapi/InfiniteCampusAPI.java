package me.caden2k3.infinitecampusapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Cleanup;
import lombok.Getter;
import me.caden2k3.infinitecampusapi.district.DistrictInfo;
import me.caden2k3.infinitecampusapi.exception.InvalidCredentialsException;
import nu.xom.Builder;
import nu.xom.ParsingException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfiniteCampusAPI {
    private String cookies = "";
    @Getter private DistrictInfo districtInfo;
    @Getter private XmlMapper mapper = new XmlMapper();

    /**
     * Queries Infinite Campus for districts.
     *
     * @param districtName The name (or part of the name) of the district.
     * @param stateCode The two letter code of the district's state.
     * @return A list of districts returned by the query.
     * @throws IOException Upon malformed URL.
     */
    public static List<DistrictInfo> searchDistricts(String districtName, String stateCode) throws IOException {
        ArrayList<DistrictInfo> districts = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        districtName = districtName.replace(" ", "%20");

        String jsonReturn = readFrom("https://mobile.infinitecampus.com/mobile/searchDistrict?query=" + districtName + "&state=" + stateCode);

        if (jsonReturn.contains("No results found")) {
            return districts;
        }

        Map<String,List<Map>> dataMap = mapper.readValue(jsonReturn, new TypeReference<Map<String,List<Map>>>(){});

        for (Map infoMap : dataMap.get("data")) {
            DistrictInfo info = new DistrictInfo();
            info.setId((Integer) infoMap.get("id"));
            info.setDistrictName((String) infoMap.get("district_name"));
            info.setDistrictAppName((String) infoMap.get("district_app_name"));
            info.setDistrictBaseURL((String) infoMap.get("district_baseurl"));
            info.setDistrictCode((String) infoMap.get("district_code"));
            info.setStateCode((String) infoMap.get("state_code"));

            districts.add(info);
        }

        return districts;
    }

    public InfiniteCampusAPI(String districtCode) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            districtInfo = mapper.readValue(new URL("https://mobile.infinitecampus.com/mobile/checkDistrict?districtCode=" + districtCode), DistrictInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getStudent(String username, String password) throws IOException, InvalidCredentialsException, ParsingException {
        //Ensure valid credentials.
        if (!checkCredentials(username, password))
            throw new InvalidCredentialsException();

        URL infoURL = new URL(districtInfo.getDistrictBaseURL() + "/prism?x=portal.PortalOutline&appName=" + districtInfo.getDistrictAppName());
        String content = new Builder().build(new ByteArrayInputStream(getContent(infoURL, false).getBytes()))
                .getRootElement()
                .getFirstChildElement("PortalOutline")
                .getFirstChildElement("Family")
                .getFirstChildElement("Student").toXML();

        return mapper.readValue(content, Student.class);
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

    private static String readFrom(String url) throws IOException {
        @Cleanup InputStream is = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
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
                if (builder2.length() > 0)
                    builder2.append("; ");

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
