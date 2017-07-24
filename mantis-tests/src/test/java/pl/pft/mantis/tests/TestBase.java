package pl.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File(
                app.getProperty("ftp.configFileDir") + app.getProperty("ftp.configFile")),
                app.getProperty("ftp.configFile"),
                app.getProperty("ftp.configFileBak"));
    }

    @BeforeSuite
    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) {
        return true;
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore(
                app.getProperty("ftp.configFileBak"),
                app.getProperty("ftp.configFile")
        );
        app.stop();
    }
}
