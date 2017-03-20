package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Yulia on 2/28/2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Group1", null, null));
    }
  }

  @Test
  public void testGroupModification() {

    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getId(),"Group1Edited", "Group1Edited", "Group2");
    app.getGroupHelper().modifyGroup(index, group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }

}
