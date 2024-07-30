package digdir.dc24_eu_wallet.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentationResponseDTOTest {

    private PresentationResponseDTO presentationResponseDTO;

    @BeforeEach
    void setUp() {
        presentationResponseDTO = new PresentationResponseDTO();
    }

    @AfterEach
    void tearDown() {
        presentationResponseDTO = null;
    }

    @Test
    void getId() {
        presentationResponseDTO.setId("testId");
        assertEquals("testId", presentationResponseDTO.getId());
    }

    @Test
    void setId() {
        assertNull(presentationResponseDTO.getId());
        presentationResponseDTO.setId("newId");
        assertEquals("newId", presentationResponseDTO.getId());
    }

    @Test
    void getCallbackUrl() {
        presentationResponseDTO.setCallbackUrl("testCallbackUrl");
        assertEquals("testCallbackUrl", presentationResponseDTO.getCallbackUrl());
    }

    @Test
    void setCallbackUrl() {
        assertNull(presentationResponseDTO.getCallbackUrl());
        presentationResponseDTO.setCallbackUrl("newCallbackUrl");
        assertEquals("newCallbackUrl", presentationResponseDTO.getCallbackUrl());
    }

    @Test
    void getExpiresTime() {
        presentationResponseDTO.setExpiresTime("testExpiresTime");
        assertEquals("testExpiresTime", presentationResponseDTO.getExpiresTime());
    }

    @Test
    void setExpiresTime() {
        assertNull(presentationResponseDTO.getExpiresTime());
        presentationResponseDTO.setExpiresTime("newExpiresTime");
        assertEquals("newExpiresTime", presentationResponseDTO.getExpiresTime());
    }

    @Test
    void getDidcommUri() {
        presentationResponseDTO.setDidcommUri("testDidcommUri");
        assertEquals("testDidcommUri", presentationResponseDTO.getDidcommUri());
    }

    @Test
    void setDidcommUri() {
        assertNull(presentationResponseDTO.getDidcommUri());
        presentationResponseDTO.setDidcommUri("newDidcommUri");
        assertEquals("newDidcommUri", presentationResponseDTO.getDidcommUri());
    }
}