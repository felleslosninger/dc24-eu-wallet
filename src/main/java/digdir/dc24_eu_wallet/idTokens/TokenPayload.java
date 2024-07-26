package digdir.dc24_eu_wallet.idTokens;

import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.TokenHead;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;

/**
 * Responsible for decoding the Oidc id token of the user logged in to
 * ansattporten or idporten, and returning it upon request.
 *
 * @Author Langbakk
 * @Version 23.07.2024
 */
public class TokenPayload {

    //Switch out with your own class from id porten
    //private TokenHeadIdPorten;
    private final OidcIdToken token;

    /**
     *
     * @param token Oidc id token, encoded in Base64. Contains header,
     *              payload and verify signature.
     */
    public TokenPayload(OidcIdToken token) {
       this.token = token;
    }

    /**
     * Method that returns a string of the payload of the token of logged in user.
     *
     * @return string of the token payload part.
     */
    public String getTokenPayloadAsString() {
        String encodedToken = token.getTokenValue();

        //Separates the header, payload and signature.
        String[] chunks = encodedToken.split("\\.");

        //Initializes decoder, which we will use to decode the payload part.
        Base64.Decoder decoder = Base64.getUrlDecoder();

        return new String(decoder.decode(chunks[1]), StandardCharsets.UTF_8);
    }

    /**
     * Reads the token from ansattporten into an object.
     *
     * @param payload the payload part of the id token.
     * @return object of ansattporten token payload part.
     */
    public TokenHead getTokenHeadAnsattporten(String payload) {
        //Using tool Gson, we will read the decoded token into classes created
        //for containing the information in the token.
        Gson gson = new Gson();

        //Parses from JSON into object. In this instance it will parse payload
        //into class TokenHead, which is the topmost class of our token.
        return gson.fromJson(payload, TokenHead.class);
    }

    /**
     * Use your own class to parse into JSON, Return in your type of
     * object representation of the json structure you get from
     * id porten-
     *
     * @return
    public TokenHeadIdPorten getTokenHeadIdPOrten(){
        //Using tool Gson, we will read the decoded token into classes created
        //for containing the information in the token.
        Gson gson = new Gson();

        //Parses from JSON into object. In this instance it will parse payload
        //into class TokenHead, which is the topmost class of our token.
        tokenHeadIdPorten = gson.fromJson(payload, TokenHeadIdPorten.class);

        return tokenPayloadAsObject;
    }
    */
}