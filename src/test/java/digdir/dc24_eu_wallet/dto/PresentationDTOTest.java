package digdir.dc24_eu_wallet.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PresentationDTOTest {

    private PresentationDTO presentationDTO;

    @BeforeEach
    void setUp() {
        presentationDTO = new PresentationDTO();
    }

    @AfterEach
    void tearDown() {
        presentationDTO = null;
    }

    @Test
    void getPresentationType() {
        presentationDTO.setPresentationType("testPresentationType");
        assertEquals("testPresentationType", presentationDTO.getPresentationType());
    }

    @Test
    void setPresentationType() {
        assertNull(presentationDTO.getPresentationType());
        presentationDTO.setPresentationType("newPresentationType");
        assertEquals("newPresentationType", presentationDTO.getPresentationType());
    }

    @Test
    void getChallengeId() {
        presentationDTO.setChallengeId("testChallengeId");
        assertEquals("testChallengeId", presentationDTO.getChallengeId());
    }

    @Test
    void setChallengeId() {
        assertNull(presentationDTO.getChallengeId());
        presentationDTO.setChallengeId("newChallengeId");
        assertEquals("newChallengeId", presentationDTO.getChallengeId());
    }

    @Test
    void getClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("claim1", "value1");
        claims.put("claim2", "value2");
        presentationDTO.setClaims(claims);
        assertEquals(claims, presentationDTO.getClaims());
    }

    @Test
    void setClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("newClaim1", "newValue1");
        claims.put("newClaim2", "newValue2");
        presentationDTO.setClaims(claims);
        assertEquals(claims, presentationDTO.getClaims());
    }

    @Test
    void isVerified() {
        presentationDTO.setVerified(true);
        assertTrue(presentationDTO.isVerified());
    }

    @Test
    void setVerified() {
        presentationDTO.setVerified(true);
        assertTrue(presentationDTO.isVerified());
        presentationDTO.setVerified(false);
        assertFalse(presentationDTO.isVerified());
    }

    @Test
    void getHolder() {
        presentationDTO.setHolder("testHolder");
        assertEquals("testHolder", presentationDTO.getHolder());
    }

    @Test
    void setHolder() {
        assertNull(presentationDTO.getHolder());
        presentationDTO.setHolder("newHolder");
        assertEquals("newHolder", presentationDTO.getHolder());
    }

    @Test
    void getPresentation() {
        PresentationDTO.Presentation presentation = new PresentationDTO.Presentation();
        presentationDTO.setPresentation(presentation);
        assertEquals(presentation, presentationDTO.getPresentation());
    }

    @Test
    void setPresentation() {
        PresentationDTO.Presentation presentation = new PresentationDTO.Presentation();
        assertNull(presentationDTO.getPresentation());
        presentationDTO.setPresentation(presentation);
        assertEquals(presentation, presentationDTO.getPresentation());
    }
}