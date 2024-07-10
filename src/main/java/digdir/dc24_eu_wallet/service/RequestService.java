package digdir.dc24_eu_wallet.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.apache.hc.client5.http.fluent.Request;


import java.io.IOException;
import java.util.Date;


@Service
@Slf4j
@NoArgsConstructor
@RequiredArgsConstructor
public class RequestService {
    @Value("${MATTR_AUDIENCE}")
    private String audienceUrl;

    @Value("${MATTR_ISSUER}")
    private String mattrUrl;

    @Value("${MATTR_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${MATTR_CLIENT_ID}")
    private String clientId;

    private String mattrJwt;


    private String authenticateMattr() throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("audience", mattrUrl);
        json.addProperty("grant_type", "client_credentials");
        json.addProperty("client_id", clientId);
        json.addProperty("client_secret", clientSecret);

        String responseContent = Request.post(audienceUrl)
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

    public String getJwt() throws IOException {
        if(mattrJwt == null) {
            return authenticateMattr();
        }
        DecodedJWT decodedJWT = JWT.decode(mattrJwt);
        if (decodedJWT.getExpiresAt() != null) {
            boolean isExpired = decodedJWT.getExpiresAt().before(new Date());
            if(isExpired) {
                return authenticateMattr();
            } else {
                return mattrJwt;
            }
        }
        return authenticateMattr();
    }
}
