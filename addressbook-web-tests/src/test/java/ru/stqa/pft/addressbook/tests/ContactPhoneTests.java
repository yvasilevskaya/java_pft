package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Yulia on 3/26/2017.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0){
      app.goTo().HomePage();
      app.contact().create(new ContactData()
              .withName("Yulia").withLastName("LastName").withEmail("Test@gmail.com")
              .inGroup(groups.iterator().next()));
      app.goTo().returnToHomePage();
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }


  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
