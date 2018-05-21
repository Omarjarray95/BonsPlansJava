/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facebook;

import static Facebook.Facebook.service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

/**
 *
 * @author Tamojit9
 */
public class Query {
    protected static String query = "";

    public static void setQuery(String query) {
        Query.query = query;
    }
    
    public static JsonObject sendQuery(Verb type) throws IOException {
        OAuthRequest request = new OAuthRequest(type, Configuration.PROTECTED_RESOURCE_URL + query);
        service.signRequest(Configuration.accessToken, request);
        Response response = request.send();
        JsonObject authData = new JsonParser().parse(response.getBody()).getAsJsonObject();
        return authData;
    }
}
