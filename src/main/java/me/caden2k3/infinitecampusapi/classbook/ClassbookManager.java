package me.caden2k3.infinitecampusapi.classbook;

import nu.xom.Element;
import nu.xom.Elements;

import java.util.ArrayList;

public class ClassbookManager {
    ArrayList<PortalClassbook> portalclassbooks = new ArrayList<PortalClassbook>();

    public ClassbookManager(Element classbook) {
        Elements e = classbook.getChildElements("PortalClassbook");
        for (int i = 0; i < e.size(); i++)
            portalclassbooks.add(new PortalClassbook(e.get(i)));
    }

    public String getInfoString() {
        String str = "";
        for (PortalClassbook p : portalclassbooks)
            str += "\n" + p.getInfoString();
        return str;
    }
}
