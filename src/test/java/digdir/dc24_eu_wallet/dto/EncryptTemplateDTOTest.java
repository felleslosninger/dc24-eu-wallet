package digdir.dc24_eu_wallet.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncryptTemplateDTOTest {

    private EncryptTemplateDTO encryptTemplateDTO;
    private String senderDidUrl;
    private List<String> recipientDidUrls;
    private EncryptTemplateDTO.Payload payload;

    @BeforeEach
    void setUp() {
        senderDidUrl = "did:example:sender";
        recipientDidUrls = Arrays.asList("did:example:recipient1", "did:example:recipient2");
        payload = new EncryptTemplateDTO.Payload();
        payload.setBody(new EncryptTemplateDTO.Body());
        payload.setFrom("testFrom");
        payload.setId("testId");
        payload.setType("testType");
        payload.setTo(Arrays.asList("testTo1", "testTo2"));
        payload.setCreated_time(3);

        encryptTemplateDTO = new EncryptTemplateDTO(senderDidUrl, recipientDidUrls, payload);
    }

    @AfterEach
    void tearDown() {
        encryptTemplateDTO = null;
    }

    @Test
    void getSenderDidUrl() {
        assertEquals(senderDidUrl, encryptTemplateDTO.getSenderDidUrl());
    }

    @Test
    void setSenderDidUrl() {
        String newSenderDidUrl = "did:example:sender";
        encryptTemplateDTO.setSenderDidUrl(newSenderDidUrl);
        assertEquals(newSenderDidUrl, encryptTemplateDTO.getSenderDidUrl());
    }

    @Test
    void getRecipientDidUrls() {
        assertEquals(recipientDidUrls, encryptTemplateDTO.getRecipientDidUrls());
    }

    @Test
    void setRecipientDidUrls() {
        List<String> newRecipientDidUrls = Arrays.asList("did:example:recipient1", "did:example:recipient2");
        encryptTemplateDTO.setRecipientDidUrls(newRecipientDidUrls);
        assertEquals(newRecipientDidUrls, encryptTemplateDTO.getRecipientDidUrls());
    }

    @Test
    void getPayload() {
        assertEquals(payload, encryptTemplateDTO.getPayload());
    }

    @Test
    void setPayload() {
        EncryptTemplateDTO.Payload newPayload = new EncryptTemplateDTO.Payload();
        newPayload.setBody(new EncryptTemplateDTO.Body());
        payload.setFrom("newFrom");
        payload.setId("newId");
        payload.setType("newType");
        payload.setTo(Arrays.asList("newTo1", "newTo2"));
        payload.setCreated_time(3);
        encryptTemplateDTO.setPayload(newPayload);
        assertEquals(newPayload, encryptTemplateDTO.getPayload());
    }
}