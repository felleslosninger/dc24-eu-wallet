package digdir.dc24_eu_wallet.repository;

import digdir.dc24_eu_wallet.entities.Challengers;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for {@link Challengers} entities.
 * Extends {@link CrudRepository} to provide basic CRUD operations for the management of class type entities.
 * This interface can be extended to include more complex queries specific to the class type entity as needed.
 *
 * @author Daniel Neset
 * @version 12.07.2024
 */
public interface ChallengersRepository extends CrudRepository<Challengers, Integer> {
  Challengers findChallengersByChallenger(String challenger);
}
