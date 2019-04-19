package me.caden2k3.infinitecampusapi.classbook;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ClassbookManager {
    private ArrayList<PortalClassbook> portalClassbooks = new ArrayList<>();

    public String getInfoString() {
        String str = "";
        for (PortalClassbook p : portalClassbooks)
            str += "\n" + p.getInfoString();
        return str;
    }
}
