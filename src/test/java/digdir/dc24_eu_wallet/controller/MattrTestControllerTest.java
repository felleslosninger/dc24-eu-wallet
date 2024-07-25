package digdir.dc24_eu_wallet.controller;

import digdir.dc24_eu_wallet.service.RequestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MattrTestControllerTest {

    @InjectMocks
    private MattrTestController mattrTestController;

    @Mock
    private RequestService requestService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void test1() throws IOException {
        when(requestService.getJwt()).thenReturn("dummyJwtToken");

        String result = mattrTestController.test();

        assertEquals("Token: dummyJwtToken", result);

        verify(requestService, times(1)).getJwt();
    }

    @Test
    void test2() {
        String result = mattrTestController.test2();

        assertEquals("test", result);
    }
}