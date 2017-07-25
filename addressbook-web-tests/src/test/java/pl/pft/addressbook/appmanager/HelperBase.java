package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;

public class HelperBase {

    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) throws MalformedURLException {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void clear(By locator) {
        wd.findElement(locator).clear();
    }

    protected void sendKeys(By locator, String text) {
        wd.findElement(locator).sendKeys(text);
    }

    protected void type(By locator, String text) {
        click(locator);
        if(text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if(! text.equals(existingText)) {
                clear(locator);
                sendKeys(locator, text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if(file != null) {
            sendKeys(locator, file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
