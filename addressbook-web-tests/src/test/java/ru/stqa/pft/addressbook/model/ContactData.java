package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String lastname;
  private final String group;
  private final String email;

  public ContactData(String name, String lastname, String email, String group) {
    this.name = name;
    this.lastname = lastname;
    this.email = email;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() { return group; }
}
