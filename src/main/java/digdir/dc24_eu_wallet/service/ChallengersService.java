package digdir.dc24_eu_wallet.service;

import digdir.dc24_eu_wallet.entities.Challengers;
import digdir.dc24_eu_wallet.repository.ChallengersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Challengers.
 * Provides CRUD operations on {@link Challengers} entities through the {@link ChallengersRepository}.
 *
 * @author Daniel Neset
 * @version 12.07.2024
 */
@Service
public class ChallengersService {
  private final ChallengersRepository challengersRepository;

  /**
   * Constructs an instance of ChallengersService with necessary dependency.
   *
   * @param challengersRepository The repository handling airport operations.
   */
  @Autowired
  public ChallengersService(ChallengersRepository challengersRepository) {
    this.challengersRepository = challengersRepository;
  }

  /**
   * Adds a new challenger to the database.
   *
   * @param challengers The {@link Challengers} to be added.
   */
  public void saveChallenger(Challengers challengers) {
    challengersRepository.save(challengers);
  }

  /**
   * Retrieves a challenger from the database based on the ChallengerId.
   *
   * @return Return a {@link Challengers} entity.
   */
  public Challengers getRecord(String challengerId) {
    return challengersRepository.findChallengersByChallenger(challengerId);
  }

}
