package ru.stqa.pft.addressbook;


import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreatin() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Group1", "Group1", "Group1"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
