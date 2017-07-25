package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;

import java.net.MalformedURLException;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager app) throws MalformedURLException {
        super(app);
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
        wd.findElement(By.cssSelector("#content input[name='submit'][value='Enter information']")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        wd.findElement(By.cssSelector("#content input[name='new'][value='New group']")).click();
    }

    public void deleteSelectedGroup() {
        wd.findElement(By.cssSelector("#content input[name='delete'][value='Delete group(s)']")).click();
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
    }

    public void initGroupModification() {
        wd.findElement(By.cssSelector("#content input[name='edit'][value='Edit group']")).click();
    }

    public void submitGroupModification() {
        wd.findElement(By.cssSelector("#content input[name='update'][value='Update']")).click();
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.cssSelector("#content input[name='selected[]']"));
    }

    public int count() {
        return wd.findElements(By.cssSelector("#content input[name='selected[]']")).size();
    }

    public void createIfRequired(GroupData group) throws MalformedURLException {
        new NavigationHelper(app).groupPage();
        if(!isThereAGroup()) {
            create(group);
        }
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }
}
