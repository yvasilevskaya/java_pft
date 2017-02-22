package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Yulia on 2/23/2017.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd=wd;
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void gotoAddNewPage() {
    wd.findElement(By.linkText("add new")).click();
  }
}
