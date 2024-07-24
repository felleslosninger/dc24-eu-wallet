package digdir.dc24_eu_wallet.controller;

import digdir.dc24_eu_wallet.component.SendWebCred;
import digdir.dc24_eu_wallet.service.ChallengersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CallbackControllerTest {

    @InjectMocks
    private CallbackController callbackController;

    @Mock
    private ChallengersService challengersService;

    @Mock
    private SendWebCred sendWebCred;

    private  AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void postCallback() {

    }
}