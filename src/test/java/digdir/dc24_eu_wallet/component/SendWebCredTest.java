package digdir.dc24_eu_wallet.component;

import digdir.dc24_eu_wallet.dto.CredentialDTO;
import digdir.dc24_eu_wallet.service.HttpService;
import digdir.dc24_eu_wallet.service.RequestService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SendWebCredTest {

    private SendWebCred sendWebCred;
    private HttpService httpService;
    private RequestService requestService;

    @BeforeEach
    void setUp() {
        httpService = mock(HttpService.class);
        requestService = mock(RequestService.class);

        sendWebCred = spy(new SendWebCred(httpService, requestService));
    }

    @AfterEach
    void tearDown() {
        sendWebCred = null;
    }

    // Missing test case
    @Test
    void createAndSendCredentials() throws IOException {
        when(httpService.postRequest(anyString(), anyString(), anyString())).thenReturn("success");

        when(requestService.getJwt()).thenReturn("dummyJwtToken");

        String walletDID = "exampleWalletDID";
        CredentialDTO credentialDTO = new CredentialDTO();

        sendWebCred.createAndSendCredentials(walletDID, credentialDTO);
    }
}