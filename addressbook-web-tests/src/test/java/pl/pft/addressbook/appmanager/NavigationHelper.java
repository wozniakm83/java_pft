package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) throws MalformedURLException {
        super(app);
    }

    public void groupPage() {
        if(isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void homePage() {
        if(isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }
}
