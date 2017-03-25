package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Yulia on 2/28/2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().allContacts().size() == 0)
      app.contact().create(new ContactData()
              .withName("Yulia").withLastName("LastName").withEmail("Test@gmail.com").withGroup("[none]"));
     app.goTo().HomePage();
    }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().allContacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();
    Contacts after = app.contact().allContacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
  }




