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
    Stylist testStylist = new Stylist("Thai");
    Stylist testStylist1 = new Stylist("BBQ");
    testStylist.save();
    testStylist1.save();
    assertEquals(2, Stylist.all().size());
  }

}
