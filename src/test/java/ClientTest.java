import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Client.all().size(), 0);
  }

  @Test
  public void save_addsAllInstancesOfClientToList() {
    Client testClient = new Client("Lardo", 1);
    Client testClient1 = new Client("Grassa", 2);
    testClient.save();
    testClient1.save();
    assertEquals(2, Client.all().size());
  }

  @Test
  public void equals_returnsTrueIfSame(){
    Client firstClient = new Client("Mike", 1);
    Client secondClient = new Client("Mike", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  // @Test
  // public void updateName_changesClientName() {
  //   Client testClient = new Client("Lardo");
  //   testClient.save();
  //   testClient.updateName("Bunk");
  //   assertEquals("Bunk", testClient.getName());
  // }


  // @Test
  // public void delete_removesClientFromDatabase() {
  //   Client testClient = new Client("Lardo");
  //   testClient.save();
  //   testClient.delete();
  //   assertEquals(0, Client.all().size());
  // }
  //
  // @Test
  // public void find_findsInstanceOfClientById() {
  //   Client testClient = new Client("Bunk");
  //   testClient.save();
  //   assertEquals(Client.find(testClient.getId()), testClient);
  // }
  //
  // @Test
  // public void assigndStylist_assignsStylistToTheClient() {
  //   Client testClient = new Client("Bunk");
  //   testClient.save();
  //   Stylist testStylist = new Stylist("Sandwiches");
  //   testStylist.save();
  //   testClient.assignStylist(testStylist.getId());
  //   assertEquals(testClient.getStylistId(), testStylist.getId());
  // }
}
