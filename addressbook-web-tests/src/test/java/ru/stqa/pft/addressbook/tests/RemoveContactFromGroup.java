package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Yulia on 4/17/2017.
 */
public class RemoveContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().GroupPage();
    Contacts contacts = app.db().contacts();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("Group1").withHeader("Group1").withFooter("Group1"));
    }
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0){
      app.goTo().HomePage();
      app.contact().create(new ContactData()
              .withName("Yulia").withLastName("LastName").withEmail("Test@gmail.com")
              .inGroup(groups.iterator().next()));
      app.goTo().returnToHomePage();
    }
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() == 0) {
        app.goTo().HomePage();
        Groups group = app.db().groups();
        ContactData modifiedContact = contacts.iterator().next();
        GroupData addedGroup = group.iterator().next();
        app.contact().selectContactById(modifiedContact.getId());
        app.contact().addContactToGroup(modifiedContact.getId());
      }
    }
  }

  @Test
  public void testRemoveContactFromGroup(){
    Contacts before = app.db().contacts();
    app.goTo().HomePage();
    ContactData modifiedContact = before.iterator().next();
    GroupData removeGroup = modifiedContact.getGroups().iterator().next();
    app.contact().removeContactFromGroup(removeGroup.getId());
    app.contact().selectContactById(modifiedContact.getId());
    app.contact().click(By.cssSelector("input[name='remove']"));
    Contacts after = app.db().contacts();
    after.remove(modifiedContact);
    ContactData modifiedContact2 = new ContactData();
    for(ContactData contact : before) {
      if (contact.equals(modifiedContact)) {
        contact.getGroups().remove(removeGroup);
        after.add(contact);
      }
    }
    modifiedContact2.getGroups().remove(removeGroup);
    System.out.println(modifiedContact2);
    assertThat(after, equalTo(before));
  }
}

