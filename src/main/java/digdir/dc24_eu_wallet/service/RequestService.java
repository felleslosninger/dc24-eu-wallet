package digdir.dc24_eu_wallet.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.apache.hc.client5.http.fluent.Request;


import java.io.IOException;
import java.util.Date;

/**
 * Service class for handling requests to the Mattr API.
 */
@Service
@Slf4j
public class RequestService {

    // Mattr API configuration properties.
    private final String audienceUrl;
    private final String mattrUrl;
    private final String clientSecret;
    private final String clientId;
    private String mattrJwt = null;

    /**
     * Constructor to initialize the RequestService with Mattr API configuration properties.
     *
     * @param audienceUrl the audience URL for the Mattr API.
     * @param mattrUrl the Mattr API URL.
     * @param clientSecret the client secret for authentication.
     * @param clientId the client ID for authentication.
     */
    public RequestService(@Value("${MATTR_AUDIENCE}") String audienceUrl,
                          @Value("${MATTR_ISSUER}") String mattrUrl,
                          @Value("${MATTR_CLIENT_SECRET}") String clientSecret,
                          @Value("${MATTR_CLIENT_ID}") String clientId) {
        this.audienceUrl = audienceUrl;
        this.mattrUrl = mattrUrl;
        this.clientSecret = clientSecret;
        this.clientId = clientId;
    }

    /**
     * Authenticates with the Mattr API and retrieves a new JWT token.
     *
     * @return the new JWT token.
     * @throws IOException if an error occurs during the request.
     */
    private String authenticateMattr() throws IOException {
        log.info("Generating new access token");

        // Create JSON payload for authentication request.
        JsonObject json = new JsonObject();
        json.addProperty("audience", audienceUrl);
        json.addProperty("grant_type", "client_credentials");
        json.addProperty("client_id", clientId);
        json.addProperty("client_secret", clientSecret);

        String postUrl = mattrUrl+"/oauth/token";
        log.info("Posting to: {}", postUrl);

        // Send POST request to Mattr API for token.
        String responseContent = Request.post(postUrl)
                .bodyString(json.toString(), ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

        // Parse the response JSON
        JsonObject responseObject = JsonParser.parseString(responseContent).getAsJsonObject();
        if (responseObject.get("access_token") == null) {
            log.error("No access token found");
            throw new IOException("No access token found");
        }

        mattrJwt = responseObject.get("access_token").getAsString();
        return mattrJwt;
    }

    /**
     * Retrieves the cached JWT token, or authenticates with Mattr API to get a new one if necessary.
     *
     * @return the JWT token.
     * @throws IOException if an error occurs during the request.
     */
    public String getJwt() throws IOException {
        log.info("Getting access token");

        // Check if the JWT token is cached.
        if(mattrJwt == null) {
            log.info("No access token found");
            return authenticateMattr();
        }

        // Decode the JWT token to check its expiration.
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

        // If expiration date is not available, re-authenticate.
        return authenticateMattr();
    }
}
