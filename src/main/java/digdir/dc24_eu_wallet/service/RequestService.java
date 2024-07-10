package digdir.dc24_eu_wallet.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.apache.hc.client5.http.fluent.Request;


import java.io.IOException;
import java.util.Date;

/**
 * Service class for interacting with external APIs.
 *
 * @author Brage Kvamme
 */
@Service
@Slf4j
public class RequestService {
    private final String audienceUrl;
    private final String mattrUrl;
    private final String clientSecret;
    private final String clientId;

    private String mattrJwt = null;

    @Autowired
    public RequestService(EnvService envService) {
        audienceUrl = envService.getAudienceUrl();
        mattrUrl = envService.getMattrUrl();
        clientSecret = envService.getClientSecret();
        clientId = envService.getClientId();
    }

    /**
     * Sends a request to the token endpoint of the mattrUrl variable. Updates the mattrJwt and returns it
     * @return Mattr JWT
     * @throws IOException Something went wrong while sending the request
     */
    private String authenticateMattr() throws IOException {
        log.info("Generating new access token");
        JsonObject json = new JsonObject();
        json.addProperty("audience", audienceUrl);
        json.addProperty("grant_type", "client_credentials");
        json.addProperty("client_id", clientId);
        json.addProperty("client_secret", clientSecret);

        String postUrl = mattrUrl+"/oauth/token";

        log.info("Posting to: {}", postUrl);
        String responseContent = Request.post(postUrl)
                .bodyString(json.toString(), ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
        JsonObject responseObject = JsonParser.parseString(responseContent).getAsJsonObject();
        if (responseObject.get("access_token") == null) {
            log.error("No access token found");
            throw new IOException("No access token found");
        }
        mattrJwt = responseObject.get("access_token").getAsString();
        return mattrJwt;
    }

    /**
     * Checks if the mattr JWT access token is present and valid. If token is present and valid,
     * return it. If not, generate a new one.0
     *
     * @return Mattr JWT access token
     * @throws IOException Something went wrong with sending the request
     */
    public String getJwt() throws IOException {
        log.info("Getting access token");
        if(mattrJwt == null) {
            log.info("No access token found");
            return authenticateMattr();
        }
        DecodedJWT decodedJWT = JWT.decode(mattrJwt);
        if (decodedJWT.getExpiresAt() != null) {
            boolean isExpired = decodedJWT.getExpiresAt().before(new Date());
            if(isExpired) {
                log.info("Access token is expired");
                return authenticateMattr();
            } else {
                log.info("Access token is valid");
                return mattrJwt;
            }
        }
        return authenticateMattr();
    }
}