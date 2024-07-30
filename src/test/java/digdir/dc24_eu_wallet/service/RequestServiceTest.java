package digdir.dc24_eu_wallet.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequestServiceTest {

    private RequestService requestService;

    @BeforeEach
    void setUp() {
        String audienceUrl = "http://example.com/audience";
        String mattrUrl = "http://example.com/mattr";
        String clientSecret = "dummySecret";
        String clientId = "dummyClientId";

        requestService = new RequestService(audienceUrl, mattrUrl, clientSecret, clientId);
    }

    @AfterEach
    void tearDown() {
        requestService = null;
    }

    @Test
    void getJwt() {
    }
}