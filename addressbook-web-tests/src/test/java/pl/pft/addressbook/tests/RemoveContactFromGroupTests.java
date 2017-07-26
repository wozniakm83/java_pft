package pl.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RemoveContactFromGroupTests extends TestBase {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void AssignContactToGroupTests() {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
        wd.findElement(By.cssSelector("body")).click();
        if (!wd.findElement(By.xpath("//form[@id='right']/select//option[3]")).isSelected()) {
            wd.findElement(By.xpath("//form[@id='right']/select//option[3]")).click();
        }
        if (!wd.findElement(By.id("16")).isSelected()) {
            wd.findElement(By.id("16")).click();
        }
        wd.findElement(By.name("remove")).click();
        wd.findElement(By.linkText("group page \"Test1\"")).click();
        wd.findElement(By.cssSelector("body")).click();
        if (!wd.findElement(By.xpath("//form[@id='right']/select//option[3]")).isSelected()) {
            wd.findElement(By.xpath("//form[@id='right']/select//option[3]")).click();
        }
        wd.findElement(By.id("container")).click();
        if (!wd.findElement(By.id("16")).isSelected()) {
            wd.findElement(By.id("16")).click();
        }
        if (!wd.findElement(By.xpath("//div[@class='right']/select//option[2]")).isSelected()) {
            wd.findElement(By.xpath("//div[@class='right']/select//option[2]")).click();
        }
        wd.findElement(By.name("add")).click();
        wd.findElement(By.linkText("group page \"Test1\"")).click();
    }
}
