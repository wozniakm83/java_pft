package pl.pft.addressbook.appmanager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public final Properties properties;
    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private DbHelper dbHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbHelper = new DbHelper();
        contactHelper = new ContactHelper(this);
        groupHelper = new GroupHelper(this);
        navigationHelper = new NavigationHelper(this);
        sessionHelper = new SessionHelper(this);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public WebDriver getDriver() throws MalformedURLException {
        if(wd == null) {
            if ("".equals(properties.getProperty("selenium.server"))) {
                if (browser.equals(BrowserType.FIREFOX)) {
                    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
                } else if (browser.equals(BrowserType.CHROME)) {
                    wd = new ChromeDriver();
                } else if (browser.equals(BrowserType.IE)) {
                    wd = new InternetExplorerDriver();
                }
                wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            } else {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(browser);
                capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win7")));
                wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
            }
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
    }
}
