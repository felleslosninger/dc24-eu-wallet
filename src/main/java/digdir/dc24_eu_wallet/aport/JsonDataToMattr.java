package digdir.dc24_eu_wallet.aport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonDataToMattr {

    private MattrObjectHead mattrObjectHead;

    private TokenHead tokenHead;

    public JsonDataToMattr(TokenPayload token) {
        this.tokenHead = token.getTokenAsObject();
        this.mattrObjectHead = new MattrObjectHead(tokenHead);
    }

    public String getJsonString(){
        // Pretty print to wiev it better.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        // Converts Java object to String
        return gson.toJson(mattrObjectHead);
    }
}
