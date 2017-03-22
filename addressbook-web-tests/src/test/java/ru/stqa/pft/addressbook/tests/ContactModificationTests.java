package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Yulia on 2/28/2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().ContactList().size() == 0) {
      app.contact().create(new ContactData()
              .withName("Yulia").withLastname("LastName").withEmail("Test@gmail.com").withGroup("[none]"));
      app.goTo().returnToHomePage();
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.contact().allContacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withName("YuliaEdited").withLastname("LastNameEdited").withEmail("testemailedited@gmail.com").withGroup(null);
    app.contact().modify(contact);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().allContacts();
    assertEquals(after.size(), before.size() );
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
