package digdir.dc24_eu_wallet.aport.fromAnsattporten;

import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.AutorizationDetails;
import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.TokenHead;
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
        tokenHead = new TokenHead();
    }

    @AfterEach
    void tearDown() {
        tokenHead = null;
    }

    @Test
    void getAmr() {
        ArrayList<String> amr = new ArrayList<>();
        amr.add("testAmr");
        tokenHead.setAmr(amr);
        assertEquals(amr, tokenHead.getAmr());
    }

    @Test
    void setAmr() {
        ArrayList<String> amr = new ArrayList<>();
        amr.add("newAmr");
        tokenHead.setAmr(amr);
        assertEquals(amr, tokenHead.getAmr());
    }

    @Test
    void getAuthorizationDetails() {
        List<AutorizationDetails> authorizationDetails = new ArrayList<>();
        AutorizationDetails details = new AutorizationDetails();
        authorizationDetails.add(details);
        tokenHead.setAuthorizationDetails(authorizationDetails);
        assertEquals(authorizationDetails, tokenHead.getAuthorizationDetails());
    }

    @Test
    void setAuthorizationDetails() {
        List<AutorizationDetails> initialAuthorizationDetails = new ArrayList<>();
        AutorizationDetails initialDetails = new AutorizationDetails();
        initialAuthorizationDetails.add(initialDetails);
        tokenHead.setAuthorizationDetails(initialAuthorizationDetails);
        assertEquals(initialAuthorizationDetails, tokenHead.getAuthorizationDetails());

        List<AutorizationDetails> updatedAuthorizationDetails = new ArrayList<>(initialAuthorizationDetails);
        AutorizationDetails newDetails = new AutorizationDetails();
        updatedAuthorizationDetails.add(newDetails);
        tokenHead.setAuthorizationDetails(updatedAuthorizationDetails);
        assertEquals(updatedAuthorizationDetails, tokenHead.getAuthorizationDetails());
        assertEquals(2, tokenHead.getAuthorizationDetails().size());
    }

    @Test
    void getSub() {
        tokenHead.setSub("testSub");
        assertEquals("testSub", tokenHead.getSub());
    }

    @Test
    void getIss() {
        tokenHead.setIss("testIss");
        assertEquals("testIss", tokenHead.getIss());
    }

    @Test
    void getPid() {
        tokenHead.setPid("testPid");
        assertEquals("testPid", tokenHead.getPid());
    }

    @Test
    void getLocale() {
        tokenHead.setLocale("testLocale");
        assertEquals("testLocale", tokenHead.getLocale());
    }

    @Test
    void getNonce() {
        tokenHead.setNonce("testNonce");
        assertEquals("testNonce", tokenHead.getNonce());
    }

    @Test
    void getAud() {
        tokenHead.setAud("testAud");
        assertEquals("testAud", tokenHead.getAud());
    }

    @Test
    void getAcr() {
        tokenHead.setAcr("testAcr");
        assertEquals("testAcr", tokenHead.getAcr());
    }

    @Test
    void getAuthTime() {
        tokenHead.setAuthTime(3);
        assertEquals(3, tokenHead.getAuthTime());
    }

    @Test
    void getName() {
        tokenHead.setName("testName");
        assertEquals("testName", tokenHead.getName());
    }

    @Test
    void getExp() {
        tokenHead.setExp(5);
        assertEquals(5, tokenHead.getExp());
    }

    @Test
    void getIat() {
        tokenHead.setIat(7);
        assertEquals(7, tokenHead.getIat());
    }

    @Test
    void getJti() {
        tokenHead.setJti("testJti");
        assertEquals("testJti", tokenHead.getJti());
    }

    @Test
    void setSub() {
        assertNull(tokenHead.getSub());
        tokenHead.setSub("newSub");
        assertEquals("newSub", tokenHead.getSub());
    }

    @Test
    void setIss() {
        assertNull(tokenHead.getIss());
        tokenHead.setIss("newIss");
        assertEquals("newIss", tokenHead.getIss());
    }

    @Test
    void setPid() {
        assertNull(tokenHead.getPid());
        tokenHead.setPid("newPid");
        assertEquals("newPid", tokenHead.getPid());
    }

    @Test
    void setLocale() {
        assertNull(tokenHead.getLocale());
        tokenHead.setLocale("newLocale");
        assertEquals("newLocale", tokenHead.getLocale());
    }

    @Test
    void setNonce() {
        assertNull(tokenHead.getNonce());
        tokenHead.setNonce("newNonce");
        assertEquals("newNonce", tokenHead.getNonce());
    }

    @Test
    void setAud() {
        assertNull(tokenHead.getAud());
        tokenHead.setAud("newAud");
        assertEquals("newAud", tokenHead.getAud());
    }

    @Test
    void setAcr() {
        assertNull(tokenHead.getAcr());
        tokenHead.setAcr("newAcr");
        assertEquals("newAcr", tokenHead.getAcr());
    }

    @Test
    void setAuthTime() {
        assertEquals(0, tokenHead.getAuthTime());
        tokenHead.setAuthTime(9);
        assertEquals(9, tokenHead.getAuthTime());
    }

    @Test
    void setName() {
        assertNull(tokenHead.getName());
        tokenHead.setName("newName");
        assertEquals("newName", tokenHead.getName());
    }

    @Test
    void setExp() {
        assertEquals(0, tokenHead.getExp());
        tokenHead.setExp(10);
        assertEquals(10, tokenHead.getExp());
    }

    @Test
    void setIat() {
        assertEquals(0, tokenHead.getIat());
        tokenHead.setIat(12);
        assertEquals(12, tokenHead.getIat());
    }

    @Test
    void setJti() {
        assertNull(tokenHead.getJti());
        tokenHead.setJti("newJti");
        assertEquals("newJti", tokenHead.getJti());
    }
}