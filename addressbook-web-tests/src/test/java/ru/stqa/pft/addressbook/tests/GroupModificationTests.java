package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Yulia on 2/28/2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0){
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("Group1"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    int index = before.size() - 1;
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("Group1Edited").withHeader("Group1Edited").withFooter("Group2");
    app.goTo().GroupPage();
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }

}
