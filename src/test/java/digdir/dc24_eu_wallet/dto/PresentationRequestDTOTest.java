package digdir.dc24_eu_wallet.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentationRequestDTOTest {

    private PresentationRequestDTO presentationRequestDTO;

    @BeforeEach
    void setUp() {
        presentationRequestDTO = new PresentationRequestDTO();
    }

    @AfterEach
    void tearDown() {
        presentationRequestDTO = null;
    }

    @Test
    void getChallenge() {
        presentationRequestDTO.setChallenge("testChallenge");
        assertEquals("testChallenge", presentationRequestDTO.getChallenge());
    }

    @Test
    void setChallenge() {
        assertNull(presentationRequestDTO.getChallenge());
        presentationRequestDTO.setChallenge("newChallenge");
        assertEquals("newChallenge", presentationRequestDTO.getChallenge());
    }

    @Test
    void getDid() {
        presentationRequestDTO.setDid("testDid");
        assertEquals("testDid", presentationRequestDTO.getDid());
    }

    @Test
    void setDid() {
        assertNull(presentationRequestDTO.getDid());
        presentationRequestDTO.setDid("newDid");
        assertEquals("newDid", presentationRequestDTO.getDid());
    }

    @Test
    void getTemplateId() {
        presentationRequestDTO.setTemplateId("testTemplateId");
        assertEquals("testTemplateId", presentationRequestDTO.getTemplateId());
    }

    @Test
    void setTemplateId() {
        assertNull(presentationRequestDTO.getTemplateId());
        presentationRequestDTO.setTemplateId("newTemplateId");
        assertEquals("newTemplateId", presentationRequestDTO.getTemplateId());
    }

    @Test
    void getCallbackUrl() {
        presentationRequestDTO.setCallbackUrl("testCallbackUrl");
        assertEquals("testCallbackUrl", presentationRequestDTO.getCallbackUrl());
    }

    @Test
    void setCallbackUrl() {
        assertNull(presentationRequestDTO.getCallbackUrl());
        presentationRequestDTO.setCallbackUrl("newCallbackUrl");
        assertEquals("newCallbackUrl", presentationRequestDTO.getCallbackUrl());
    }
}