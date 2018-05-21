/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Facebook;

import static Facebook.Configuration.token;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Tamojit9
 */
public class Facebook {

    static OAuthService service;
    private static final Token EMPTY_TOKEN = null;
    private static Boolean[] sets = new Boolean[3];
    private static Boolean serialize = false;
    private static File serializeTo;

        static {
           sets[0] = sets[1] = sets[2] = false;
       }

       private Facebook() {
       }

       private static void handlePreRequisites() throws Exception {
           if(!sets[0]) {
               throw new Exception("App-Id/App-Key not set");
           } else if(!sets[1]) {
               throw new Exception("App-Secret not set");
           } else if(!sets[2]) {
               throw new Exception("Redirect Url not set");
           } 
       }

       private static void buildService() throws Exception {
           handlePreRequisites();
           service = new ServiceBuilder()
                   .provider(FacebookApi.class)
                   .apiKey(Configuration.apiKey)
                   .apiSecret(Configuration.apiSecret)
                   .callback(Configuration.redirectUrl)
                   .scope(Configuration.scopes)
                   .build();
       }

    //=================================================Query functions=============================================
    
        public static JsonObject getQuery(String query) throws IOException {
            Query.setQuery(query);
            return Query.sendQuery(Verb.GET);
        }

        public static JsonObject postQuery(String query) throws IOException {
            Query.setQuery(query);
            return Query.sendQuery(Verb.POST);
        }

        public static JsonObject putQuery(String query) throws IOException {
            Query.setQuery(query);
            return Query.sendQuery(Verb.PUT);
        }

        public static JsonObject deleteQuery(String query) throws IOException {
            Query.setQuery(query);
            return Query.sendQuery(Verb.DELETE);
        }

    //=================================================Query functions=============================================
    
    //===================================================authorizing and login functions============================
    
        @SuppressWarnings("empty-statement")
        public static String getAuthorizationUrl() throws Exception {
            buildService();

            String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
            showLoginFlow(authorizationUrl);

            return authorizationUrl;
        }

        public static Token getOAuthToken() throws Exception {
            if(token == null || token.isEmpty()) {
                throw new Exception("Token is NULL");
            }

            Verifier verifier = new Verifier(token);

            Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);

            return accessToken;
        }

        public static void login() throws Exception {
            getAuthorizationUrl();
        }

        public static Token deSerializeToken(File to) throws IOException, FileNotFoundException, Exception {
            Token token2 = null;
            if (to.exists()) {
                try {
                    Persistent.PersistentStorage.load(to);
                    if (Persistent.PersistentStorage.get("token") != null) {
                        token2 = (Token) Persistent.PersistentStorage.get("token");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Facebook.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return token2;
        }

        public static void serializeToken(File to) throws Exception {
            if(Configuration.accessToken == null) {
                login();
            } else {
                Persistent.PersistentStorage.put("token", Configuration.accessToken);
                Persistent.PersistentStorage.store(to);
            }
        }

        private static void showLoginFlow(String url) {
            Browser.Browser.load(url, true);
        }
    
    //===================================================authorizing and login functions============================
    
    //==============================================get and set methods=========================================
    
        //==============================================serialization===============================================
        
            public static void setSerializeToFile(File serializeTo) {
                Facebook.serializeTo = serializeTo;
            }

            public static File getSerializeToFile() {
                return serializeTo;
            }

            public static Boolean isSerialize() {
                return serialize;
            }

            public static void setSerialize(Boolean serialize) {
                Facebook.serialize = serialize;
            }

            public static Token getSerializableOAuthToken() throws Exception {
                if(serializeTo == null) {
                    throw new Exception("serializeTo file not set");
                }

                Token token2 = deSerializeToken(serializeTo);
                if(token2 != null) {
                    buildService();
                    Configuration.accessToken = token2;
                }
                return token2;
            }
            
        //==============================================serialization===============================================

        public static Facebook getFacebookInstance() {
            return new Facebook();
        }

        //=========================================connection preRequisites============================================
        
            public static void setAppId(String appId) {
                sets[0] = true;
                Configuration.apiKey = appId;
            }

            public static void setAppSecret(String appSecret) {
                sets[1] = true;
                Configuration.apiSecret = appSecret;
            }

            public static void appPermissions(List<String> permissions) {
                StringBuilder scopes = new StringBuilder();
                for (String string : permissions) {
                    scopes.append(string).append(",");
                }
                Configuration.scopes = scopes.toString();
                Configuration.scopes = Configuration.scopes.substring(0, Configuration.scopes.length()-1);
            }

            public static void appPermissions(String[] permissions) {
                StringBuilder scopes = new StringBuilder();
                for (String string : permissions) {
                    scopes.append(string).append(",");
                }
                Configuration.scopes = scopes.toString();
                Configuration.scopes = Configuration.scopes.substring(0, Configuration.scopes.length()-1);
            }

            public static void setRedirectUrl(String redirUrl) {
                sets[2] = true;
                Configuration.redirectUrl =redirUrl;
            }

        //=========================================connection preRequisites============================================
    
    //==============================================get and set methods=========================================
}
