package digdir.dc24_eu_wallet.idTokens.toMattr;

import digdir.dc24_eu_wallet.idTokens.fromDigdirporten.AutorizationDetails;
import digdir.dc24_eu_wallet.idTokens.fromDigdirporten.TokenHead;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MattrObjectHeadTest {

    private MattrObjectHead mattrObjectHead;
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

        mattrObjectHead = new MattrObjectHead(tokenHead);
    }

    @AfterEach
    void tearDown() {
        mattrObjectHead = null;
        tokenHead = null;
    }

    @Test
    void getFormattedJsonData() {
    }

    @Test
    void getToken() {
    }

    @Test
    void getCredential() {
    }
}