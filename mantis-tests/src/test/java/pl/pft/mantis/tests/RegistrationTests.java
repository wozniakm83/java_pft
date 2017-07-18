package pl.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration() {
        app.registration().start(app.getProperty("web.userLogin"), app.getProperty("web.userEmail"));
    }
}
