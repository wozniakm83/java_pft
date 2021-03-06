package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;

import java.net.MalformedURLException;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager app) throws MalformedURLException {
        super(app);
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
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        attach(By.name("photo"), contactData.getPhoto());

        if(creation) {
            if(contactData.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } else {
                Assert.assertFalse(isElementPresent((By.name("new_group"))));
            }
        }
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitContactModification() {
        wd.findElement(By.cssSelector("#content input[name='update']")).click();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void submitContactDeletion() {
        wd.findElement(By.cssSelector("#content input[type='button'][value='Delete']")).click();
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, creation);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        submitContactDeletion();
        confirmContactDeletion();
        contactCache = null;
        returnToHomePage();
    }

    public void displayContactsInGroup(ContactData contact) {
        new Select(wd.findElement(By.cssSelector("#right > select[name='group']"))).selectByVisibleText(contact.getGroup());
    }


    //

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("#maintable input[name='selected[]']"));
    }

    public void viewContact(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

    public int count() {
        return wd.findElements(By.cssSelector("#maintable input[name='selected[]']")).size();
    }

/*    public void createIfRequired(ContactData contact, boolean creation) throws MalformedURLException {
        new NavigationHelper(app).homePage();
        if(! isThereAContact()) {
            create(contact, creation);
        }
    }*/

    public void createIfRequired(boolean creation) throws MalformedURLException {
        new NavigationHelper(app).homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                            .withFirstname(app.properties.getProperty("contact.defaultFirstname"))
                            .withLastname(app.properties.getProperty("contact.defaultLastname"))
                            .withAddress(app.properties.getProperty("contact.defaultAddress"))
                            .withEmail(app.properties.getProperty("contact.defaultEmail"))
                            .withHomePhone(app.properties.getProperty("contact.defaultHomePhone")),
                    true);
        }
    }


        private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3)
                    .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public ContactData infoFromViewForm(ContactData contact) {
        viewContact(contact.getId());
        String contactInfo = wd.findElement(By.id("content")).getText();
        contactInfo = contactInfo
                .replaceFirst("\\s", ";")
                .replace("H: ", "")
                .replace("M: ", "")
                .replace("W: ", "")
                .replace("Member of: ", "")
                .replaceAll("\\n", ";");
        String[] split = contactInfo.split(";");
        System.out.println(contactInfo);
        return new ContactData()
                .withFirstname(split[0])
                .withLastname(split[1])
                .withAddress(split[2])
                .withHomePhone(split[4])
                .withMobilePhone(split[5])
                .withWorkPhone(split[6])
                .withEmail(split[8])
                .withEmail2(split[9])
                .withEmail3(split[10])
                .withGroup(split[13]);
    }
}

