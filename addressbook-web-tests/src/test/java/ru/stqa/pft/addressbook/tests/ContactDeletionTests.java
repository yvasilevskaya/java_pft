package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Yulia on 2/28/2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeDeleteConfirmationForm();
    app.getNavigationHelper().gotoHomePage();
  }
}