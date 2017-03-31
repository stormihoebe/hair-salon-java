import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private int stylist_id;
  private String name;
  private String description;

  public Client(int stylist_id, String name, String description) {
    this.stylist_id = stylist_id;
    this.name = name;
    this.description = description;
  }

  public int getStylistId(){
    return stylist_id;
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

  public static List<Client> all() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM clients;";
        return con.createQuery(sql)
          .executeAndFetch(Client.class);
      }
    }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (stylist_id, name, description) VALUES (:stylist_id, :name, :description)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("stylist_id", stylist_id)
        .addParameter("name", name)
        .addParameter("description", description)
        .executeUpdate()
        .getKey();
    }
  }
  public static Client find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM clients WHERE id = :id;";
        Client client = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Client.class);
        return client;
      }
    }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName());
    }
  }
}
