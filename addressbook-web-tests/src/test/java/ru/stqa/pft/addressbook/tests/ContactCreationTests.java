package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("Group1"));
    }
  }

  @Test
  public void testContactCreation() {
    app.goTo().HomePage();
    Contacts before = app.contact().allContacts();
    ContactData contact = new ContactData()
            .withName("Yulia").withLastName("LastName").withEmail("Test@gmail.com").withGroup("Group1");
    app.contact().create(contact);
    app.goTo().HomePage();
    Contacts after = app.contact().allContacts();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after,equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) ->  g.getId()).max().getAsInt()))));
  }
}
