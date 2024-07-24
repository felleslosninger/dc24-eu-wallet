package digdir.dc24_eu_wallet.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpServiceTest {

    private HttpService httpService;

    @BeforeEach
    void setUp() {
        httpService = new HttpService();
    }

    @AfterEach
    void tearDown() {
        httpService = null;
    }

    @Test
    void postRequest() {
    }

    @Test
    void close() {
    }
}