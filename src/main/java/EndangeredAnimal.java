import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal {
  public String name;
  public int id;
  // public boolean endangered;
  private String health;
  private String age;

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

  // public void updateHealth(String health) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE endangered_animals SET health=:health WHERE id=:id;";
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .addParameter("health", health)
  //       .executeUpdate();
  //   }
  // }
  //
  // public void updateAge(String age) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE endangered_animals SET age=:age WHERE id=:id;";
  //     con.createQuery(sql)
  //       .addParameter("age", age)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }

  public void update(String health, String age) {
    this.health = health;
    this.age = age;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE endangered_animals SET (health=:health, age=:age) WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .addParameter("id", id)
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

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM endangered_animals WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }


}
