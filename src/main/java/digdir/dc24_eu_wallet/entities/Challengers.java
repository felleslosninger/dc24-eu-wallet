package digdir.dc24_eu_wallet.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * This entity represents a challenger.
 * It contains a unique id, unique challenger id and
 * a json string containing a user credentials.
 *
 * @author Daniel Neset
 * @version 12.07.2024
 */
@Setter
@Getter
@Entity
public class Challengers {

    /**
     * -- GETTER --
     *  Get the unique id.
     * <p>
     *
     * -- SETTER --
     *  Set the unique id.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * -- GETTER --
     *  Get the unique challenger.
     * <p>
     *
     * -- SETTER --
     *  Set the unique challenger.
     *
     */
    @Column(nullable = false, unique = true)
    private String challenger;

    /**
     * -- GETTER --
     *  Get the Json string.
     * <p>
     *
     * -- SETTER --
     *  Set the Json string.
     *
     */
    @Column(nullable = false, length = 65535, columnDefinition = "TEXT")
    private String jsonData;
}
