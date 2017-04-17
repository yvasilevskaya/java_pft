package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Yulia on 4/11/2017.
 */
public class AddContactToGroup extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().GroupPage();
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
  }

  @Test
  public void testAddContactToGroup(){
    Contacts before = app.db().contacts();
    app.goTo().HomePage();
    Groups group = app.db().groups();
    ContactData modifiedContact = before.iterator().next();
    GroupData addedGroup = group.iterator().next();
    app.contact().selectContactById(modifiedContact.getId());
    app.contact().addContactToGroup(modifiedContact.getId());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact.inGroup(addedGroup))));
  }
}
