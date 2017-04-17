package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulia on 4/2/2017.
 */
public class ContactDataGeneration {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGeneration generator = new ContactDataGeneration();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    }else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    }else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    }else {
      System.out.println("Unrecognized format "+ format);
    }
  }
  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withName(String.format("Yulia %s", i)).withLastName(String.format("LastName%s", i)).withAddress(String.format("Minsk%s", i))
              .withHomePhone(String.format("12345%s", i)).withMobilePhone(String.format("1111111%s", i))
              .withWorkPhone(String.format("11 22%s", i)).withEmail(String.format("Test@gmail.com%s", i))
              .withEmail2(String.format("Test2@gmail.com%s", i)).withEmail3(String.format("Test3@gmail.com%s", i)));
    }
    return contacts;
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;\n", contact.getName(), contact.getLastName(), contact.getAddress(),
              contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone(),
              contact.getEmail(), contact.getEmail2(),contact.getEmail3()));
    }
    writer.close();
  }
}
