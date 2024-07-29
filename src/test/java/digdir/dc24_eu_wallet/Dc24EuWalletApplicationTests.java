package digdir.dc24_eu_wallet;

import digdir.dc24_eu_wallet.service.RequestService;

import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RequiredArgsConstructor
class Dc24EuWalletApplicationTests {

	@Autowired
	private RequestService requestService;

    @Test
	void contextLoads() {
		assertNotNull(requestService, "requestService should not be null");
	}

	@Test
	void fetchAccessTokenFromMattr() throws IOException {
	}
}
