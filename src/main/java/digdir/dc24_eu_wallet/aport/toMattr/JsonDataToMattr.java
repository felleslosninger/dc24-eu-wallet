package digdir.dc24_eu_wallet.aport.toMattr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.TokenHead;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.TokenPayload;

/**
 * Responsible for creating the payload of the request to MATTR that issues something. It can be for example a right
 * to do something in the instance of ansattporten, but for other services it can be for example a ticket to a movie.
 *
 */

public class JsonDataToMattr {

    private MattrObjectHead mattrObjectHead;

    private TokenHead tokenHead;

    /**
     * Constructs JsonDataToMattr, and initializes tokenhead and mattrObjectHead. TokenHead is the topmost object of
     * the OIDC id token represented as a java object. mattrObjectHead is the topmost object of the payload of the
     * request to MATTR for issuing. The mattrTokenObject is inserted into the mattrObjectHead, because we use data
     * from the TokenHead in the request payload.
     *
     * @param token
     */

    public JsonDataToMattr(TokenPayload token) {
        tokenHead = token.getTokenAsObject();
        mattrObjectHead = new MattrObjectHead(tokenHead);
        System.out.println("ckeck check check "+ mattrObjectHead.getCred());

    }

    /**
     * Create the JSON formatted string based on the objects for the payload. This is done using GSON.
     *
     * @return JSON formatted string with the content for the payload of the issuing request to MATTR.
     */
    public String getJsonString(){
        // Creates the Gson object which will format the java object to a json string.
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        System.out.println("mattr object head " +gson.toJson(mattrObjectHead));
        // Converts Java object to String
        return gson.toJson(mattrObjectHead);
    }
}
