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
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withName("Yulia").withLastName("LastName").withGroup("[none]").withAddress("Minsk")
            .withHomePhone("123 123").withMobilePhone("+37529").withWorkPhone("11 22")
            .withEmail("Test@gmail.com").withEmail2("Test2@gmail.com").withEmail3("Test3@gmail.com");
    app.contact().create(contact);
    app.goTo().HomePage();
    assertThat(app.contact().сount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after,equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) ->  g.getId()).max().getAsInt()))));
  }
}
