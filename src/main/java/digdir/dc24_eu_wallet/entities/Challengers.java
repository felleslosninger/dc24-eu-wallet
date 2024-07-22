package digdir.dc24_eu_wallet.entities;

import jakarta.persistence.*;

/**
 * This entity represents a challenger.
 * It contains a unique id, unique challenger id and
 * a json string containing a user credentials.
 *
 * @author Daniel Neset
 * @version 12.07.2024
 */
@Entity
public class Challengers {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false, unique = true)
  private String challenger;
  @Column(nullable = false, length = 65535, columnDefinition = "TEXT")
  private String jsonData;

  /**
   * Get the unique id.
   *
   * @return Return the unique id.
   */
  public long getId() {
    return id;
  }

  /**
   * Set the unique id.
   *
   * @param id The unique id to be set.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Get the unique challenger.
   *
   * @return Return the unique challenger.
   */
  public String getChallenger() {
    return challenger;
  }

  /**
   * Set the unique challenger.
   *
   * @param challenger The unique challenger to be set.
   */
  public void setChallenger(String challenger) {
    this.challenger = challenger;
  }

  /**
   * Get the Json string.
   *
   * @return Return the json string.
   */
  public String getJsonData() {
    return jsonData;
  }

  /**
   * Set the Json string.
   *
   * @param jsonData Set the json string.
   */
  public void setJsonData(String jsonData) {
    this.jsonData = jsonData;
  }
}
