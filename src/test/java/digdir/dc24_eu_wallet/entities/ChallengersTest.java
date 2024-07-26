package digdir.dc24_eu_wallet.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChallengersTest {

    private Challengers challengers;

    @BeforeEach
    void setUp() {
        challengers = new Challengers();
    }

    @AfterEach
    void tearDown() {
        challengers = null;
    }

    @Test
    void getId() {
        challengers.setId(1);
        assertEquals(1, challengers.getId());
    }

    @Test
    void setId() {
        assertEquals(0, challengers.getId());
        challengers.setId(2);
        assertEquals(2, challengers.getId());
    }

    @Test
    void getChallenger() {
        challengers.setChallenger("testChallenger");
        assertEquals("testChallenger", challengers.getChallenger());
    }

    @Test
    void setChallenger() {
        assertNull(challengers.getChallenger());
        challengers.setChallenger("newChallenger");
        assertEquals("newChallenger", challengers.getChallenger());
    }

    @Test
    void getJsonData() {
        challengers.setJsonData("testJsonData");
        assertEquals("testJsonData", challengers.getJsonData());
    }

    @Test
    void setJsonData() {
        assertNull(challengers.getJsonData());
        challengers.setJsonData("newJsonData");
        assertEquals("newJsonData", challengers.getJsonData());
    }
}