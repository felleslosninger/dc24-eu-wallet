package digdir.dc24_eu_wallet.idTokens;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TokenPayloadTest {

    private TokenPayload tokenPayload;

    @BeforeEach
    void setUp() {
        Instant issuedAt = Instant.now();
        Instant expiresAt = Instant.now().plusSeconds(3600);
        Map<String, Object> claims = new HashMap<>();
        claims.put("exampleClaim", "exampleValue");

        OidcIdToken oidcIdToken = new OidcIdToken(
                "exampleTokenValue",
                issuedAt,
                expiresAt,
                claims
        );

        tokenPayload = new TokenPayload(oidcIdToken);
    }

    @AfterEach
    void tearDown() {
        tokenPayload = null;
    }

    @Test
    void getTokenPayloadAsString() {
        String tokenPayloadAsString = tokenPayload.getTokenPayloadAsString();
        assertNotNull(tokenPayloadAsString);
        assertTrue(tokenPayloadAsString.contains("exampleTokenValue"));
    }

    @Test
    void getTokenHeadAnsattporten() {
    }
}