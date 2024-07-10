package digdir.dc24_eu_wallet;

import com.sun.net.httpserver.Request;
import digdir.dc24_eu_wallet.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Dc24EuWalletApplicationTests {


    @Test
	void contextLoads() {
	}

	@Test
	void fetchAccessTokenFromMattr() throws IOException {

	}

}
