package digdir.dc24_eu_wallet.aport.fromAnsattporten;

import com.google.gson.Gson;
import digdir.dc24_eu_wallet.idTokens.TokenPayload;
import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.TokenHead;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TokenPayloadTest {

    private TokenPayload tokenPayload;

    @BeforeEach
    void setUp() {
        OidcIdToken mockOidcIdToken = mock(OidcIdToken.class);

        TokenHead tokenHead = new TokenHead();
        tokenHead.setSub("exampleSub");
        Gson gson = new Gson();
        String tokenHeadJson = gson.toJson(tokenHead);

        String encodedPayload = Base64.getUrlEncoder().encodeToString(tokenHeadJson.getBytes());

        String jwtToken = "header." + encodedPayload + ".signature";

        when(mockOidcIdToken.getTokenValue()).thenReturn(jwtToken);

        tokenPayload = new TokenPayload(mockOidcIdToken);
    }

    @AfterEach
    void tearDown() {
        tokenPayload = null;
    }

    @Test
    void getTokenAsObject() {
    }
}