package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.pft.addressbook.model.GroupData;

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

    public void selectGroup() {
        wd.findElement(By.cssSelector("#content input[name='selected[]']")).click();
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
        if(! isThereAGroup()) {
            createGroup(group);
        }
    }
}
