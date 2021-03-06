package pl.pft.mantis.tests;

import org.testng.annotations.Test;
import pl.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword")));
        assertTrue(session.isLoggedInAs(app.getProperty("web.adminLogin")));
    }
}
