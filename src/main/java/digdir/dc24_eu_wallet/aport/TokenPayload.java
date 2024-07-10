package digdir.dc24_eu_wallet.aport;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

/**
 * Responsible for decoding the Oidc id token of the user logged in to
 * ansattporten and returning it upon request.
 *
 */

//todo: singleton?? Diskuter om singleton gjelder for en "session".

public class TokenPayload {

    private TokenHead tokenPayloadAsObject;

    private String payload;

    private OidcIdToken token;

    /**
     *
     * @param token Oidc id token, encoded in Base64. Contains header,
     *              payload and verify signature.
     */

    public TokenPayload(OidcIdToken token) {
       this.token = token;
    }

    /**
     * Method that returns the object of the payload part of the token.
     *
     * @return TokenHead, which is the object representation of the payload
     * part of the token. This is in "plain text".
     */

    public TokenHead getTokenAsObject(){

        String encodedToken = token.getTokenValue();

        //Separates the header, payload and signature.
        String[] chunks = encodedToken.split("\\.");

        //Initializes decoder, which we will use to decode the payload part.
        Base64.Decoder decoder = Base64.getUrlDecoder();

        try {
            //Tries to decode the payload part.
            payload = new String(decoder.decode(chunks[1]), "utf-8");
        }catch (UnsupportedEncodingException e){
            LoggerFactory.getLogger(TokenPayload.class).error("Error decoding token", e);
        }

        //Using tool Gson, we will read the decoded token into classes created
        //for containing the information in the token.
        Gson gson = new Gson();

        //Parses from JSON into object. In this instance it will parse payload
        //into class TokenHead, which is the topmost class of our token.
        tokenPayloadAsObject = gson.fromJson(payload, TokenHead.class);

        return tokenPayloadAsObject;
    }
}
