package digdir.dc24_eu_wallet.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialSignDTOTest {

    private CredentialSignDTO credentialSignDTO;
    private CredentialSignDTO.Credential credential;
    private CredentialSignDTO.CredentialStatus credentialStatus;

    @BeforeEach
    void setUp() {
        credentialSignDTO = new CredentialSignDTO();
        credential = new CredentialSignDTO.Credential();
        credentialStatus = new CredentialSignDTO.CredentialStatus();
    }

    @AfterEach
    void tearDown() {
        credentialSignDTO = null;
        credential = null;
        credentialStatus = null;
    }

    @Test
    void getId() {
        credentialSignDTO.setId("testId");
        assertEquals("testId", credentialSignDTO.getId());
    }

    @Test
    void setId() {
        assertNull(credentialSignDTO.getId());
        credentialSignDTO.setId("newId");
        assertEquals("newId", credentialSignDTO.getId());
    }

    @Test
    void getTag() {
        credentialSignDTO.setTag("testTag");
        assertEquals("testTag", credentialSignDTO.getTag());
    }

    @Test
    void setTag() {
        assertNull(credentialSignDTO.getTag());
        credentialSignDTO.setTag("newTag");
        assertEquals("newTag", credentialSignDTO.getTag());
    }

    @Test
    void getCredential() {
        credentialSignDTO.setCredential(credential);
        assertEquals(credential, credentialSignDTO.getCredential());
    }

    @Test
    void setCredential() {
        assertNull(credentialSignDTO.getCredential());
        credentialSignDTO.setCredential(credential);
        assertEquals(credential, credentialSignDTO.getCredential());
    }

    @Test
    void getCredentialStatus() {
        credentialSignDTO.setCredentialStatus(credentialStatus);
        assertEquals(credentialStatus, credentialSignDTO.getCredentialStatus());
    }

    @Test
    void setCredentialStatus() {
        assertNull(credentialSignDTO.getCredentialStatus());
        credentialSignDTO.setCredentialStatus(credentialStatus);
        assertEquals(credentialStatus, credentialSignDTO.getCredentialStatus());
    }

    @Test
    void getIssuanceDate() {
        credentialSignDTO.setIssuanceDate("2015-01-01");
        assertEquals("2015-01-01", credentialSignDTO.getIssuanceDate());
    }

    @Test
    void setIssuanceDate() {
        assertNull(credentialSignDTO.getIssuanceDate());
        credentialSignDTO.setIssuanceDate("2016-01-01");
        assertEquals("2016-01-01", credentialSignDTO.getIssuanceDate());
    }
}