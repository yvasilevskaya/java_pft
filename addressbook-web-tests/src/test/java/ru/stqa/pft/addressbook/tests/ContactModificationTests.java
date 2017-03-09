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
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Yulia", "LastName", "Test@gmail.com", "[none]"));
      app.getNavigationHelper().returnToHomePage();
    }
    app.getContactHelper().editSelectedContact();
    app.getContactHelper().fillContactForm(new ContactData("YuliaEdited", "LastNameEdited", "testemailedited@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
