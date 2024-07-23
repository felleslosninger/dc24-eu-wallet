package digdir.dc24_eu_wallet.aport.toMattr;

import digdir.dc24_eu_wallet.aport.fromAnsattporten.TokenHead;
import digdir.dc24_eu_wallet.dto.CredentialDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MattrObjectHeadTest {

    private MattrObjectHead mattrObjectHead;
    private TokenHead tokenHead;

    @BeforeEach
    void setUp() {
        tokenHead = new TokenHead();
        mattrObjectHead = new MattrObjectHead(tokenHead);
    }

    @AfterEach
    void tearDown() {
        mattrObjectHead = null;
    }

    @Test
    void getFormattedJsonData() {
        String jsonData = mattrObjectHead.getFormattedJsonData();
        assertNotNull(jsonData);
    }

    @Test
    void getToken() {
        assertEquals(tokenHead, mattrObjectHead.getToken());
    }

    @Test
    void getCredential() {
        CredentialDTO expectedCredential = new CredentialDTO();
        CredentialDTO actualCredential = mattrObjectHead.getCredential();

        assertEquals(expectedCredential.getCred(), actualCredential.getCred());
    }
}