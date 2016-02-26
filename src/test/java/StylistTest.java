import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void save_addsAllInstancesOfStylistToList() {
    Stylist testStylist = new Stylist("Jack");
    Stylist testStylist1 = new Stylist("Jill");
    testStylist.save();
    testStylist1.save();
    assertEquals(2, Stylist.all().size());
  }

  @Test
  public void find_findStylistInDatabase_true() {
    Stylist newStylist = new Stylist("James");
    newStylist.save();
    Stylist savedStylist = Stylist.find(newStylist.getId());
    assertTrue(newStylist.equals(savedStylist));
  }

  @Test
  public void updateName_changesStylistName() {
    Stylist testStylist = new Stylist("Johnny");
    testStylist.save();
    testStylist.updateName("Jamie");
    assertEquals("Jamie", testStylist.getName());
  }

  @Test
  public void delete_removesStylistFromDatabase() {
    Stylist testStylist = new Stylist("Jen");
    testStylist.save();
    testStylist.delete();
    assertEquals(0, Stylist.all().size());
  }
}
