import me.caden2k3.infinitecampusapi.InfiniteCampus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author Caden Kriese
 *
 * Created on 2019-04-03.
 */
public class DistrictTest {
    @Test
    public void retrieveDistrictInfo() throws IOException {
        final String expectedDistrictName = "Douglas County School";
        final String expectedDistrictBaseURL = "https://campus.dcsdk12.org/icprod/";
        final int id = 22065;
        final String expectedDistrictCode = "zldqcb";

        //Code for Douglas County School District, Colorado.
        InfiniteCampus core = new InfiniteCampus(InfiniteCampus.searchDistricts("Douglas County", "CO").get(0).getDistrictCode());

        Assert.assertEquals(core.getDistrictInfo().getDistrictName(), expectedDistrictName);
        Assert.assertEquals(core.getDistrictInfo().getDistrictBaseURL(), expectedDistrictBaseURL);
        Assert.assertEquals(core.getDistrictInfo().getId(), id);
        Assert.assertEquals(core.getDistrictInfo().getDistrictCode(), expectedDistrictCode);
    }
}
