import me.caden2k3.infinitecampusapi.InfiniteCampus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Caden Kriese
 *
 * Created on 2019-04-03.
 */
public class DistrictTest {
    @Test
    public void retrieveDistrictInfo() {
        final String expectedDistrictName = "Douglas County School";
        final String expectedDistrictBaseURL = "https://campus.dcsdk12.org/icprod/";
        final int id = 21722;
        final String expectedDistrictCode = "fngzxv";

        //Code for Douglas County School District, Colorado.
        InfiniteCampus core = new InfiniteCampus("fngzxv");

        Assert.assertEquals(core.getDistrictInfo().getDistrictName(), expectedDistrictName);
        Assert.assertEquals(core.getDistrictInfo().getDistrictBaseURL(), expectedDistrictBaseURL);
        Assert.assertEquals(core.getDistrictInfo().getId(), id);
        Assert.assertEquals(core.getDistrictInfo().getDistrictCode(), expectedDistrictCode);
    }
}
