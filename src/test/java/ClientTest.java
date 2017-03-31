import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Client_instantiatesCorrectly_true(){
    Client testClient = new Client(1, "name", "regular customer, short hair, trim every other weeks");
    assertTrue(testClient instanceof Client);
  }

  @Test
  public void getStylistId_clientInstantiatesWithStylistId_true(){
    Client testClient = new Client(1, "name", "regular customer, short hair, trim every other weeks");
    assertEquals(testClient.getStylistId(), 1);
  }

  @Test
  public void getName_clientInstantiatesWithName_true(){
    Client testClient = new Client(1, "name", "regular customer, short hair, trim every other weeks");
    assertEquals(testClient.getName(), "name");
  }

  @Test
  public void getDescription_clientInstantiatesWithDescription_true(){
    Client testClient = new Client(1, "name", "regular customer, short hair, trim every other weeks");
    assertEquals(testClient.getDescription(), "regular customer, short hair, trim every other weeks");
  }

  @Test
  public void all_returnsAllSavedClients_true() {
    Client clientOne = new Client(1, "name", "regular customer, short hair, trim every other weeks");
    clientOne.save();
    Client clientTwo = new Client(1, "name2", "regular customer, long hair");
    clientTwo.save();
    assertTrue(Client.all().get(0).equals(clientOne));
    assertTrue(Client.all().get(1).equals(clientTwo));
  }

  @Test
  public void save_savesClientRecordtoDatabase_true() {
    Client testClient = new Client(1, "name", "regular customer, short hair, trim every other weeks");
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void getId_clientAssignedIdOnSave_true() {
    Client testClient = new Client(1, "name", "regular customer, short hair, trim every other weeks");
    testClient.save();
    assertTrue(testClient.getId() > 1);
  }

  @Test
    public void find_returnsClientWithSameId_clientTwo() {
      Client clientOne = new Client(1, "name", "regular customer, short hair, trim every other weeks");
      clientOne.save();
      Client clientTwo = new Client(1, "name", "regular customer, Medium hair, trim every other weeks");
      clientTwo.save();
      assertEquals(Client.find(clientTwo.getId()), clientTwo);
    }
  @Test
  public void updateDescription_updatesDescriptionOfClient_true() {
    Client clientOne = new Client(1, "name", "regular customer, short hair, trim every other weeks");
    clientOne.save();
    clientOne.updateDescription("medium hair, trims ever other month");
    assertEquals("medium hair, trims ever other month", Client.find(clientOne.getId()).getDescription());
  }






}
