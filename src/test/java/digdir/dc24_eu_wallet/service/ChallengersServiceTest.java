package digdir.dc24_eu_wallet.service;

import digdir.dc24_eu_wallet.repository.ChallengersRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class ChallengersServiceTest {

    private ChallengersService service;

    private ChallengersRepository challengersRepository;

    @BeforeEach
    void setUp() {
        challengersRepository = mock(ChallengersRepository.class);
        service = new ChallengersService(challengersRepository);
    }

    @AfterEach
    void tearDown() {
        challengersRepository = null;
    }

    @Test
    void saveChallenger() {
    }

    @Test
    void getRecord() {
    }
}