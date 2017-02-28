package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Yulia on 2/28/2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().editSelectedContact();
    app.getContactHelper().fillContactForm(new ContactData("Yulia", "Edited", "Edited", "Nickname", "QA", "Test", "TestAddress", "+375291234567", "testemail@gmail.com"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
