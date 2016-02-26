import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String name;

  public Stylist (String name) {
    this.name = name;
  }

  // Stylist.find(testRestaurant.getStylistId()).getType()

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name FROM stylists";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
        this.getId() == newStylist.getId();
    }
  }

  public static Stylist find(int id) {
    String sql = "SELECT id, name FROM stylists WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stylists(name) VALUES (:name)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .executeUpdate()
      .getKey();
    }
  }

  //UPDATE
  public void updateName(String newName) {
    this.name = newName;
    String sql = "UPDATE stylists SET name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
    }
  }
}
