package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    gotoAddNewPage();
    fillContactForm(new ContactData("Yulia", "middlename", "LastName", "Nickname", "QA", "Test", "TestAddress", "+375291234567", "testemail@gmail.com"));
    enterContactCreation();
    returnToHomePage();
  }
}
