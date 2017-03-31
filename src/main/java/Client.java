import org.sql2o.*;

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
}
