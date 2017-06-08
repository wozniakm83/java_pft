package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
        wd.findElement(By.cssSelector("#content input[name='submit'][value='Enter information']")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getGroupName());
        type(By.name("group_header"), groupData.getGroupHeader());
        type(By.name("group_footer"), groupData.getGroupFooter());
    }

    public void initGroupCreation() {
        wd.findElement(By.cssSelector("#content input[name='new'][value='New group']")).click();
    }

    public void deleteSelectedGroup() {
        wd.findElement(By.cssSelector("#content input[name='delete'][value='Delete group(s)']")).click();
    }

    public void selectGroup(int index) {
        wd.findElements(By.cssSelector("#content input[name='selected[]']")).get(index).click();
    }

    public void initGroupModification() {
        wd.findElement(By.cssSelector("#content input[name='edit'][value='Edit group']")).click();
    }

    public void submitGroupModification() {
        wd.findElement(By.cssSelector("#content input[name='update'][value='Update']")).click();
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.cssSelector("#content input[name='selected[]']"));
    }

    public void createGroupIfRequired(GroupData group) {
        new NavigationHelper(wd).gotoGroupPage();
        if (!isThereAGroup()) {
            createGroup(group);
        }
    }

    public int getGroupCount() {
        return wd.findElements(By.cssSelector("#content input[name='selected[]']")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("#content span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            GroupData group = new GroupData(name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
