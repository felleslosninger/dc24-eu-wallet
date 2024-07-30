package digdir.dc24_eu_wallet.idTokens.fromDigdirporten;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenHeadTest {

    private TokenHead tokenHead;

    @BeforeEach
    void setUp() {
        ArrayList<String> amr = new ArrayList<>();
        amr.add("exampleAMR");

        List<AutorizationDetails> autorizationDetails = new ArrayList<>();

        tokenHead = new TokenHead(
                "exampleSub",
                amr,
                "exampleIss",
                "examplePid",
                "exampleLocale",
                "exampleNonce",
                "exampleAud",
                "exampleAcr",
                autorizationDetails,
                123,
                "exampleName",
                456,
                789,
                "exampleJti"
        );
    }

    @AfterEach
    void tearDown() {
        tokenHead = null;
    }

    @Test
    void getAmr() {
        ArrayList<String> expectedAmr = new ArrayList<>();
        expectedAmr.add("exampleAMR");
        assertEquals(expectedAmr, tokenHead.getAmr());
    }

    @Test
    void getAuthorizationDetails() {
        List<AutorizationDetails> autorizationDetails = new ArrayList<>();
        assertEquals(autorizationDetails, tokenHead.getAuthorizationDetails());
    }

    @Test
    void sub() {
        assertEquals("exampleSub", tokenHead.sub());
    }

    @Test
    void amr() {
        ArrayList<String> expectedAmr = new ArrayList<>();
        expectedAmr.add("exampleAMR");
        assertEquals(expectedAmr, tokenHead.amr());
    }

    @Test
    void iss() {
        assertEquals("exampleIss", tokenHead.iss());
    }

    @Test
    void pid() {
        assertEquals("examplePid", tokenHead.pid());
    }

    @Test
    void locale() {
        assertEquals("exampleLocale", tokenHead.locale());
    }

    @Test
    void nonce() {
        assertEquals("exampleNonce", tokenHead.nonce());
    }

    @Test
    void aud() {
        assertEquals("exampleAud", tokenHead.aud());
    }

    @Test
    void acr() {
        assertEquals("exampleAcr", tokenHead.acr());
    }

    @Test
    void authorization_details() {
        List<AutorizationDetails> expectedAutorizationDetails = new ArrayList<>();
        assertEquals(expectedAutorizationDetails, tokenHead.getAuthorizationDetails());
    }

    @Test
    void authTime() {
        assertEquals(123, tokenHead.authTime());
    }

    @Test
    void name() {
        assertEquals("exampleName", tokenHead.name());
    }

    @Test
    void exp() {
        assertEquals(456, tokenHead.exp());
    }

    @Test
    void iat() {
        assertEquals(789, tokenHead.iat());
    }

    @Test
    void jti() {
        assertEquals("exampleJti", tokenHead.jti());
    }
}