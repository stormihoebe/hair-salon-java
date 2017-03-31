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




}
