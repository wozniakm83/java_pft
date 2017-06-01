package pl.pft.addressbook;

import org.testng.annotations.Test;

public class DeleteGroupTests extends TestBase {

    @Test
    public void testDeleteGroup() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }
}
