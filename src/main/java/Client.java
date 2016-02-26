import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String name;
  private int stylist_id;

  public Client (String name, int stylist_id) {
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public int getId() {
    return id;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
        this.getId() == newClient.getId();
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO clients(name, stylist_id) VALUES (:name, :stylist_id)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .addParameter("stylist_id", this.stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  //READ
  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    String sql = "SELECT * FROM clients WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
    }
  }

  //Update
  public void updateName(String name) {
    this.name = name;
    String sql = "UPDATE clients SET name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void updateStylist(int stylist_id) {
    this.stylist_id = stylist_id ;
    String sql = "UPDATE clients SET stylist_id = :stylist_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  //DELETE
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
    }
  }

  public static void deleteAll(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }
}
