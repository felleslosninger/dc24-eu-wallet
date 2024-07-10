package digdir.dc24_eu_wallet.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Add other environment variables to this fila.
 *
 * @author Brage Kvamme
 */
@Service
@Getter
public class EnvService {

    private final String audienceUrl;
    private final String mattrUrl;
    private final String clientSecret;
    private final String clientId;

    @Autowired
    public EnvService(@Value("${MATTR_AUDIENCE}") String audienceUrl,
                          @Value("${MATTR_ISSUER}") String mattrUrl,
                          @Value("${MATTR_CLIENT_SECRET}") String clientSecret,
                          @Value("${MATTR_CLIENT_ID}") String clientId) {
        this.audienceUrl = audienceUrl;
        this.mattrUrl = mattrUrl;
        this.clientSecret = clientSecret;
        this.clientId = clientId;
    }
}
