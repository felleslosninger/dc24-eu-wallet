package digdir.dc24_eu_wallet.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialCardDTOTest {

    private CredentialCardDTO credentialCardDTO;

    private CredentialCardDTO.Payload testPayLoad;

    @BeforeEach
    void setUp() {
        credentialCardDTO = new CredentialCardDTO();
        testPayLoad = new CredentialCardDTO.Payload();
        testPayLoad.setName("testName");
        testPayLoad.setDescription("testDescription");
    }

    @AfterEach
    void tearDown() {
        credentialCardDTO = null;
    }

    @Test
    void getPayload() {
        credentialCardDTO.setPayload(testPayLoad);
        assertEquals(testPayLoad, credentialCardDTO.getPayload());
    }

    @Test
    void setPayload() {
        CredentialCardDTO.Payload newPayLoad = new CredentialCardDTO.Payload();
        newPayLoad.setName("newName");
        newPayLoad.setDescription("newDescription");
        credentialCardDTO.setPayload(newPayLoad);
        assertEquals(newPayLoad, credentialCardDTO.getPayload());
    }

    @Test
    void getProofType() {
        credentialCardDTO.setProofType("testProofType");
        assertEquals("testProofType", credentialCardDTO.getProofType());
    }

    @Test
    void setProofType() {
        assertNull(credentialCardDTO.getProofType());
        credentialCardDTO.setProofType("newProofType");
        assertEquals("newProofType", credentialCardDTO.getProofType());
    }

    @Test
    void getTag() {
        credentialCardDTO.setTag("testTag");
        assertEquals("testTag", credentialCardDTO.getTag());
    }

    @Test
    void setTag() {
        assertNull(credentialCardDTO.getTag());
        credentialCardDTO.setTag("newTag");
        assertEquals("newTag", credentialCardDTO.getTag());
    }

    @Test
    void isPersist() {
        credentialCardDTO.setPersist(true);
        assertTrue(credentialCardDTO.isPersist());
    }

    @Test
    void setPersist() {
        credentialCardDTO.setPersist(false);
        assertFalse(credentialCardDTO.isPersist());

        credentialCardDTO.setPersist(true);
        assertTrue(credentialCardDTO.isPersist());
    }

    @Test
    void isRevocable() {
        credentialCardDTO.setRevocable(true);
        assertTrue(credentialCardDTO.isRevocable());
    }

    @Test
    void setRevocable() {
        credentialCardDTO.setRevocable(false);
        assertFalse(credentialCardDTO.isRevocable());

        credentialCardDTO.setRevocable(true);
        assertTrue(credentialCardDTO.isRevocable());
    }

    @Test
    void isIncludeId() {
        credentialCardDTO.setIncludeId(true);
        assertTrue(credentialCardDTO.isIncludeId());
    }

    @Test
    void setIncludeId() {
        credentialCardDTO.setIncludeId(false);
        assertFalse(credentialCardDTO.isIncludeId());

        credentialCardDTO.setIncludeId(true);
        assertTrue(credentialCardDTO.isIncludeId());
    }
}