package digdir.dc24_eu_wallet.idTokens;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.TokenHead;


import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

/**
 * Responsible for decoding the Oidc id token of the user logged in to
 * ansattporten or idporten, and returning it upon request.
 *
 * @Author Langbakk
 * @Version 23.07.2024
 */
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
     * Method that returns a string of the payload of the token of logged in user.
     *
     * @return string of the token payload part.
     */
    public String getTokenPayloadAsString(){

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
        return payload;
    }

    /**
     * Reads the token from ansattporten into an object.
     *
     * @param payload the payload part of the id token.
     * @return object of ansattporten token payload part.
     */
    public TokenHead getTokenHeadAnsattporten(String payload){
        //Using tool Gson, we will read the decoded token into classes created
        //for containing the information in the token.
        Gson gson = new Gson();

        System.out.println("Payload: GetTokenhead " + payload);

        //Parses from JSON into object. In this instance it will parse payload
        //into class TokenHead, which is the topmost class of our token.
        tokenPayloadAsObject = gson.fromJson(payload, TokenHead.class);

        return tokenPayloadAsObject;
    }

    
     /*  Use your own class to parse into JSON, Return in your type of
     object representation of the json structure you get from
     id porten-
     
     @return
     */
   
    
}
