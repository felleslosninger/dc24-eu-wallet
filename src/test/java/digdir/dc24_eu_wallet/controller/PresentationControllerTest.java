package digdir.dc24_eu_wallet.controller;

import digdir.dc24_eu_wallet.service.ChallengersService;
import digdir.dc24_eu_wallet.service.HttpService;
import digdir.dc24_eu_wallet.service.RequestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentationControllerTest {

    private PresentationController presentationController;

    private ChallengersService challengersService;

    private HttpService httpService;

    private RequestService requestService;

    @BeforeEach
    void setUp() {
        presentationController = new PresentationController(challengersService, httpService, requestService);
    }

    @AfterEach
    void tearDown() {
        presentationController = null;
    }

    @Test
    void index() {
    }

    @Test
    void user() {
    }

    @Test
    void getJsonContentForMattr() {
    }

    @Test
    void getQR() {
    }

    @Test
    void logoutCallback() {
    }
}