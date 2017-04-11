import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal implements DatabaseManagement {
  private String name;
  private int id;
  private String health;
  private String age;

  public static final String ILL_HEALTH = "ill";
  public static final String OKAY_HEALTH = "okay";
  public static final String HEALTHY_HEALTH = "healthy";

  public EndangeredAnimal(String name, String health, String age) {
    this.id = id;
    this.name = name;
    this.health = health;
    this.age = age;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherEndangeredAnimal) {
    if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
      return this.getName().equals(newEndangeredAnimal.getName()) && this.getHealth().equals(newEndangeredAnimal.getHealth()) && this.getAge().equals(newEndangeredAnimal.getAge());
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO endangered_animals (name, health, age) VALUES (:name, :health, :age);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM endangered_animals;";
      return con.createQuery(sql)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM endangered_animals WHERE id=:id;";
      EndangeredAnimal endangeredanimal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(EndangeredAnimal.class);
      return endangeredanimal;
    }
  }

  public void update(String name, String health, String age) {
    this.name = name;
    this.health = health;
    this.age = age;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE endangered_animals SET name = :name, health = :health, age = :age WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("name", this.name)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE endangered_animal_id=:id;";
        List<Sighting> sightings = con.createQuery(sql)
          .addParameter("id", this.id)
          .executeAndFetch(Sighting.class);
        return sightings;
    }
  }

  @Override
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM endangered_animals WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }


}
