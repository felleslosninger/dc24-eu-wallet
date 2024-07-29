package digdir.dc24_eu_wallet.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EncryptedCredentialDTOTest {

    private EncryptedCredentialDTO encryptedCredentialDTO;

    @BeforeEach
    void setUp() {
        encryptedCredentialDTO = new EncryptedCredentialDTO();
    }

    @AfterEach
    void tearDown() {
        encryptedCredentialDTO = null;
    }

    @Test
    void getJwe() {
    }
}