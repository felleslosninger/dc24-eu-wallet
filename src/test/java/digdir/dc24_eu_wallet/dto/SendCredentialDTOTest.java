package digdir.dc24_eu_wallet.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendCredentialDTOTest {

    private SendCredentialDTO sendCredentialDTO;
    private String to;
    private SendCredentialDTO.JweMessage message;

    @BeforeEach
    void setUp() {
        to = "exampleRecipient";
        message = new SendCredentialDTO.JweMessage();
        sendCredentialDTO = new SendCredentialDTO(to, message);
    }

    @AfterEach
    void tearDown() {
        sendCredentialDTO = null;
    }

    @Test
    void getTo() {
        assertEquals(to, sendCredentialDTO.getTo());
    }

    @Test
    void setTo() {
        String newTo = "newRecipient";
        sendCredentialDTO.setTo(newTo);
        assertEquals(newTo, sendCredentialDTO.getTo());
    }

    @Test
    void getMessage() {
        assertEquals(message, sendCredentialDTO.getMessage());
    }

    @Test
    void setMessage() {
        SendCredentialDTO.JweMessage newMessage = new SendCredentialDTO.JweMessage();
        sendCredentialDTO.setMessage(newMessage);
        assertEquals(newMessage, sendCredentialDTO.getMessage());
    }
}