package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().fillContactForm(new ContactData("Yulia", "LastName", "Test@gmail.com", "Group1"), true);
    app.getContactHelper().enterContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }
}
