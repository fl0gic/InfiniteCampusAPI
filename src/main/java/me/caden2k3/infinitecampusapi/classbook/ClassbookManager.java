package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;
import nu.xom.Element;
import nu.xom.Elements;

import java.util.ArrayList;

@Getter @Setter
public class ClassbookManager {
    private ArrayList<PortalClassbook> portalClassbooks = new ArrayList<>();

    public ClassbookManager(Element classbook) {
        Elements e = classbook.getChildElements("PortalClassbook");
        for (int i = 0; i < e.size(); i++)
            portalClassbooks.add(new PortalClassbook(e.get(i)));
    }

    public String getInfoString() {
        String str = "";
        for (PortalClassbook p : portalClassbooks)
            str += "\n" + p.getInfoString();
        return str;
    }
}
