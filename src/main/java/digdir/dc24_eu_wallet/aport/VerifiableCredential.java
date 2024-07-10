package digdir.dc24_eu_wallet.aport;

import com.auth0.jwt.JWT;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import com.auth0.jwt.interfaces.DecodedJWT;

import net.minidev.json.JSONObject;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;


public class VerifiableCredential {

    private TokenHead tokenHead;

    private String informationDump;


    public VerifiableCredential(OidcIdToken token) throws UnsupportedEncodingException {

        String theToken = token.getTokenValue();

        String[] chunks = theToken.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
       // String header = new String(decoder.decode(chunks[0]), "utf-8");
        String payload = new String(decoder.decode(chunks[1]), "utf-8");

        Gson gson = new Gson();
        System.out.println(token);
        tokenHead = gson.fromJson(payload, TokenHead.class);

        System.out.println(tokenHead.getAcr());
    }
}
/**
    public String printDetails() {
        return tokenHead.getAutorizationDetailsFromToken().getAuthority();
    }
}

    private List<String> context;
    private String id;
    private List<String> type;
    private String issuer;
    private ZonedDateTime issuanceDate;
    private List<String> credentialSubject;
    private List<String> proof;
    @Getter
    private ZonedDateTime expirationDate;

    public VerifiableCredential(OidcIdToken token){
        List<String>credentials = new ArrayList<>();
        credentials.add("https://www.w3.org/2018/credentials/v1");
        credentials.add("https://example.com/credentials/v2");
        setCredentialSubject(credentials);
        setId(token);
        setExpirationDate(token.getClaim("exp"));
    }

    public void setCredentialSubject(List<String> credentialSubject) {
        this.credentialSubject = credentialSubject;
    }

    public ZonedDateTime getIssuanceDate(){
        issuanceDate = ZonedDateTime.now();
        return issuanceDate;
    }

    public void setExpirationDate(String expirationDate){
        this.expirationDate = ZonedDateTime.parse(expirationDate);
    }

    public void setId(OidcIdToken token){
        String placeholder = token.getClaim("autorization_details");
    }



    public String browseAutorizationDetails(OidcIdToken token, String searchWord){
        String autorizationDetails = token.getClaim("autorization_details");

        return "abcd";
    }

    private static void handleJsonObject(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.BEGIN_ARRAY)) {
                System.out.print("Now we start with the content: ");
                handleJsonArray(reader);
                System.out.print("]");
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
                return;
            } else {
                if (token.equals(JsonToken.NAME)) {
                    //get the current token
                    fieldname = reader.nextName();
                }

                if ("resource".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Name: "+reader.nextString());
                }

                if("type".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Age:" + reader.nextInt());
                }

                if("resource_name".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Verified:" + reader.nextBoolean());
                }
                if("reportees".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Age:" + reader.nextInt());
                }
                if("type".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Age:" + reader.nextInt());
                }
            }
        }
    }

    private static void handleJsonArray(JsonReader reader) throws IOException {
        reader.beginArray();
        String fieldname = null;

        while (true) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.END_ARRAY)) {
                reader.endArray();
                break;
            } else if (token.equals(JsonToken.BEGIN_OBJECT)) {
                handleJsonObject(reader);
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
            } else {
                System.out.print(reader.nextInt() + " ");
            }
        }
    }


}
*/