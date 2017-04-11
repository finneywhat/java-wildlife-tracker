import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;

public class Sighting {
  private int animal_id;
  private int endangered_animal_id;
  private String location;
  private String ranger_name;
  private int id;
  private Timestamp time;

  public Sighting(int animal_id, String location, String ranger_name, int endangered_animal_id) {
    this.animal_id = animal_id;
    this.endangered_animal_id = endangered_animal_id;
    this.location = location;
    this.ranger_name = ranger_name;
    this.id = id;
    this.time = time;
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public int getEndangeredAnimalId() {
    return endangered_animal_id;
  }

  public String getLocation() {
    return location;
  }

  public String getRangerName() {
    return ranger_name;
  }

  public Timestamp getTime() {
    return time;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getLocation().equals(newSighting.getLocation()) &&
             this.getRangerName().equals(newSighting.getRangerName()) &&
            //  this.getTime().equals(newSighting.getTime()) &&
             this.getAnimalId() == newSighting.getAnimalId() &&
             this.getEndangeredAnimalId() == newSighting.getEndangeredAnimalId() &&
             this.getId() == newSighting.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, location, ranger_name, endangered_animal_id, time) VALUES (:animal_id, :location, :ranger_name, :endangered_animal_id, now());";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("location", this.location)
        .addParameter("ranger_name", this.ranger_name)
        .addParameter("endangered_animal_id", this.endangered_animal_id)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Sighting.class);
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id=:id;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public String displayTime() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT time FROM sightings WHERE id = :id;";
      Timestamp time = con.createQuery(sql)
                          .addParameter("id", id)
                          .executeAndFetchFirst(Timestamp.class);
    } catch (IllegalArgumentException exception) { }
    return String.format("%1$TD %1$Tr", time);
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM sightings * WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }
}
