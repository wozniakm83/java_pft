package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;

public class CreateNewGroupTests extends TestBase {

    @Test
    public void testCreateNewGroup() {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("Test1", "Test1_header", "Test1_footer"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
