package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Yulia on 3/26/2017.
 */
public class ContactInfoPageTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withName("Yulia").withLastName("LastName").withAddress("Minsk")
              .withHomePhone("123 123").withMobilePhone("+37529").withWorkPhone("11 22")
              .withEmail("Test@gmail.com").withEmail2("Test2@gmail.com").withEmail3("Test3@gmail.com"));
      app.goTo().returnToHomePage();
    }
  }

  @Test
  public void testContactInfoPage() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();

    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

    assertThat(contactInfoFromDetailsForm.getAllInfo(), equalTo(mergeAllInfoFromContactForm(contactInfoFromEditForm)));
  }


  private String mergeAllInfoFromContactForm(ContactData contact) {
    return Arrays.asList(contact.getName()+ " ", contact.getLastName()+"\n", contact.getAddress()+"\n",
            "\nH: " + contact.getHomePhone(),"\nM: " + contact.getMobilePhone(), "\nW: " + contact.getWorkPhone()+"\n\n",
            contact.getEmail()+"\n", contact.getEmail2()+"\n", contact.getEmail3())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining());
  }
}
