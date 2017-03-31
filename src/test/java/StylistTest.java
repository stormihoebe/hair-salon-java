import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class StylistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
    public void Stylist_instantiatesCorrectly_true() {
      Stylist testStylist = new Stylist ("test name", "experienced stylist, specializes in dying/bleaching");
      assertTrue(testStylist instanceof Stylist);
    }

  @Test
    public void getName_instantiatesWithName_String() {
      Stylist testStylist = new Stylist ("test name", "experienced stylist, specializes in dying/bleaching");
      assertEquals("test name", testStylist.getName());
    }

  @Test
    public void getDescription_instantiatesWithDescriptio_String() {
      Stylist testStylist = new Stylist ("test name", "experienced stylist, specializes in dying/bleaching");
      assertEquals("experienced stylist, specializes in dying/bleaching", testStylist.getDescription());
    }
  @Test
  public void all_allReturnsAllInstancesOfStylists_true() {
    Stylist myStylistOne = new Stylist("test name", "experienced stylist, specializes in dying/bleaching");
    Stylist myStylistTwo = new Stylist ("test name 2", "experienced stylist, specializes in dying/bleaching");
    myStylistOne.save();
    myStylistTwo.save();
    assertTrue(Stylist.all().get(0).equals(myStylistOne));
    assertTrue(Stylist.all().get(1).equals(myStylistTwo));
  }
  @Test
  public void save_assignsIdToStylist_true() {
    Stylist myStylist = new Stylist ("test name", "experienced stylist, specializes in dying/bleaching");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
  public void delete_deleteStylistWithSameId_true(){
    Stylist myStylist = new Stylist ("test name", "experienced stylist, specializes in dying/bleaching");
    myStylist.save();
    int myStylistId = myStylist.getId();
    myStylist.delete();
    assertEquals(null, Stylist.find(myStylistId));
  }

  @Test
  public void find_findReturnsRoasterWithSameId_true() {
    Stylist myStylist = new Stylist ("test name", "experienced stylist, specializes in dying/bleaching");
    myStylist.save();
    assertEquals(Stylist.find(myStylist.getId()), myStylist);
  }

  @Test
  public void updateDescription_updatesDescriptionOfClient_true() {
    Stylist myStylist = new Stylist ("test name", "experienced stylist, specializes in dying/bleaching");
    myStylist.save();
    myStylist.updateDescription("Stylist of the year 2016");
    assertEquals("Stylist of the year 2016", Stylist.find(myStylist.getId()).getDescription());
  }


}
