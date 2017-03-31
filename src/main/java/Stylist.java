import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String name;
  private String description;

  public Stylist(String name, String description) {
    this.name = name;
    this.description = description;

  }

  public String getName(){
    return name;
  }

  public String getDescription(){
    return description;
  }

  public int getId(){
    return id;
  }

  public static List<Stylist> all(){
    String sql = "SELECT * FROM stylists;";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Stylist.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists(name, description) VALUES (:name, :description)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .addParameter("description", description)
      .executeUpdate()
      .getKey();
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists where id=:id;";
      Stylist roaster = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return roaster;
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()) {
     String sql = "DELETE FROM stylists WHERE id = :id;";
     con.createQuery(sql)
       .addParameter("id", id)
       .executeUpdate();
     }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)){
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName());
      // && this.getId() == newDoctor.getId();
    }
  }

}
