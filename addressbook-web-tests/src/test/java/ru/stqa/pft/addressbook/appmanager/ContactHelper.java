package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

/**
 * Created by Yulia on 2/23/2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void enterContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getName());
    type(By.name("lastname"),contactData.getLastName());
    type(By.name("email"),contactData.getEmail());

    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  //public void selectContact(int index) {
    //wd.findElements(By.name("selected[]")).get(index).click();
  //}

  public int сount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value = '"+ id + "']")).click();
  }

  public void delete() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void closeDeleteConfirmationForm() {
    wd.switchTo().alert().accept();
  }

  public void editSelectedContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm( contact, true);
    enterContactCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    editSelectedContact();
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    delete();
    closeDeleteConfirmationForm();
    contactCache = null;
  }

  //public boolean isThereAContact() {return isElementPresent(By.name("selected[]"));}

  private Contacts contactCache = null;

  public Contacts allContacts() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath("./td[3]")).getText();
      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withName(name).withLastName(lastname));
    }
    return new Contacts(contactCache);
  }


}



