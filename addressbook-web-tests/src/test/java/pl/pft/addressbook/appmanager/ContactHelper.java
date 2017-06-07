package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd)  {
        super(wd);
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void submitContactCreation() {
        wd.findElement(By.cssSelector("#content input[name='submit'][value='Enter']")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());

        if(creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent((By.name("new_group"))));
        }
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void selectContact() {
        wd.findElement(By.cssSelector("#maintable input[name='selected[]']")).click();
    }

    public void submitContactModification() {
        wd.findElement(By.cssSelector("#content input[name='update']")).click();
    }

    public void initContactModification() {
        wd.findElement(By.cssSelector("#maintable img[title='Edit']")).click();
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void submitContactDeletion() {
        wd.findElement(By.cssSelector("#content input[type='button'][value='Delete']")).click();
    }

    public void createContact(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, creation);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("#maintable input[name='selected[]']"));
    }

    public void createContactIfRequired(ContactData contact, boolean creation) {
        new NavigationHelper(wd).gotoHomePage();
        if(! isThereAContact()) {
            createContact(contact, creation);
        }
    }
}
