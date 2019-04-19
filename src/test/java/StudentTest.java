import me.caden2k3.infinitecampusapi.InfiniteCampusAPI;
import me.caden2k3.infinitecampusapi.Student;
import me.caden2k3.infinitecampusapi.exception.InvalidCredentialsException;
import nu.xom.ParsingException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import util.TestUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author Caden Kriese
 *
 * Created on 2019-04-03.
 */
public class StudentTest {
    //Values retrieved from resources/test-credentials.txt
    //That file is ignored for obvious reasons, you must put in your own credentials if you wish to run this test.
    private String[] userInfo;

    @BeforeSuite
    public void before() throws IOException {
        //Data should be formatted 'username-password'
        userInfo = TestUtils.readFile(new File(getClass().getClassLoader().getResource("test-credentials.txt").getFile()))
                .replace("\n", "").split("-");
    }

    @Test
    public void retrieveStudentInfo() throws IOException, InvalidCredentialsException, ParsingException {
        final String username = userInfo[0];
        final String password = userInfo[1];
        final String districtCode = "fngzxv";

        final InfiniteCampusAPI core = new InfiniteCampusAPI(districtCode);
        final Student student = core.getStudent(username, password);

        Assert.assertTrue(student.getCalendars() != null);
        Assert.assertTrue(student.getClassbooks() != null);
        Assert.assertTrue(student.getPrimaryCalendar() != null);
        Assert.assertTrue(student.getInfoString() != null);
    }
}
