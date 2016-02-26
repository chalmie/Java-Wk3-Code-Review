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

  @Test
  public void save_assignsIdToObject() {
    Client newClient = new Client("Mike", 1);
    newClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(newClient.getId(), savedClient.getId());
  }

  @Test
  public void find_findsInstanceOfClientById() {
    Client testClient = new Client("Mike", 2);
    testClient.save();
    assertEquals(Client.find(testClient.getId()), testClient);
  }

  // @Test
  // public void updateName_changesClientName() {
  //   Client testClient = new Client("Lardo");
  //   testClient.save();
  //   testClient.updateName("Bunk");
  //   assertEquals("Bunk", testClient.getName());
  // }


  @Test
  public void delete_removesClientFromDatabase() {
    Client testClient = new Client("Mike", 3);
    testClient.save();
    testClient.delete();
    assertEquals(0, Client.all().size());
  }

  @Test
  public void deleteAll_removesClientsFromDatabase() {
    Client testClientA = new Client("Mike", 3);
    Client testClientB = new Client("Jill", 1);
    testClientA.save();
    testClientA.save();
    Client.deleteAll();
    assertEquals(0, Client.all().size());
  }

  @Test
  public void assigndStylist_assignsStylistToTheClient() {
    Client testClient = new Client("Mike", 1);
    testClient.save();
    Stylist testStylist = new Stylist("John");
    testStylist.save();
    testClient.assignStylist(testStylist.getId());
    assertEquals(testClient.getStylistId(), testStylist.getId());
  }
}
