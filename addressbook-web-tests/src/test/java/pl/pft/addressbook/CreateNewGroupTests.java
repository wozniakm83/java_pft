package pl.pft.addressbook;

import org.testng.annotations.Test;

public class CreateNewGroupTests extends TestBase {

    @Test
    public void testCreateNewGroup() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("Test1", "Test1_header", "Test1_footer"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
