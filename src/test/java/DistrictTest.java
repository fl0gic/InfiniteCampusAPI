import me.caden2k3.infinitecampusapi.InfiniteCampusAPI;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        //For some reason Douglas County has two ids and two codes??
        final List<Integer> expectedDistrictIds = Arrays.asList(22065, 21722);
        final List<String> expectedDistrictCodes = Arrays.asList("zldqcb", "fngzxv");

        //Code for Douglas County School District, Colorado.
        InfiniteCampusAPI core = new InfiniteCampusAPI(InfiniteCampusAPI.searchDistricts("Douglas County", "CO").get(0).getDistrictCode());

        assertThat(core.getDistrictInfo().getDistrictName()).isEqualTo(expectedDistrictName);
        assertThat(core.getDistrictInfo().getDistrictBaseURL()).isEqualTo(expectedDistrictBaseURL);

        assertThat(core.getDistrictInfo().getId()).isIn(expectedDistrictIds);
        assertThat(core.getDistrictInfo().getDistrictCode()).isIn(expectedDistrictCodes);
    }
}
