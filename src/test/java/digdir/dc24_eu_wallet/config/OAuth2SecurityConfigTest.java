package digdir.dc24_eu_wallet.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.web.server.ServerHttpSecurity;

class OAuth2SecurityConfigTest {

    @InjectMocks
    private  OAuth2SecurityConfig oAuth2SecurityConfig;

    @Mock
    private ServerHttpSecurity serverHttpSecurity;

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
    void springSecurityFilterChain() {

    }
}