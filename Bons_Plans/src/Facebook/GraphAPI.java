/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Facebook;

import com.google.gson.JsonObject;
import java.io.IOException;
import org.scribe.model.Verb;

/**
 *
 * @author Tamojit9
 */
public class GraphAPI extends Query{
    static String type = "";
    
    public GraphAPI GET(String s) throws Exception 
    {
        if(!type.isEmpty()) 
        {
            throw new Exception("GET, POST, PUT, DELETE can be called only once");
        }
        type = "GET";
        query += s;
        return this;
    }
    
    public GraphAPI POST(String s) throws Exception {
        if(!type.isEmpty()) {
            throw new Exception("GET, POST, PUT, DELETE can be called only once");
        }
        type = "POST";
        query += s;
        return this;
    }
    
    public GraphAPI PUT(String s) throws Exception {
        if(!type.isEmpty()) {
            throw new Exception("GET, POST, PUT, DELETE can be called only once");
        }
        type = "PUT";
        query += s;
        return this;
    }
    
    public GraphAPI DELETE(String s) throws Exception {
        if(!type.isEmpty()) {
            throw new Exception("GET, POST, PUT, DELETE can be called only once");
        }
        type = "DELETE";
        query += s;
        return this;
    }
    
    public GraphAPI fields(String s) {
        if(query.endsWith(")")) {
            query += "," + s;
        } else {
            query += "?fields="+s;
        }
        return this;
    }
    
    public GraphAPI modifiers(String s) throws Exception {
        query += "." + s;
        return this;
    }
    
    public GraphAPI set(String s) {
        query += "(" + s + ")";
        return this;
    }
    
    public JsonObject build() throws IOException {
        //System.out.println(query);
        Verb verbType = null;
        switch (type) {
            case "GET":
                verbType = Verb.GET;
                break;
            case "POST":
                verbType = Verb.POST;
                break;
            case "DELETE":
                verbType = Verb.DELETE;
                break;
            case "PUT":
                verbType = Verb.PUT;
                break;
        }
        return sendQuery(verbType);
    }
    
    public GraphAPI AND(String s) throws Exception {
        query += "&" + s;
        return this;
    }
    
    public GraphAPI value(String s) {
        query += "=" + s;
        return this;
    }
    
    public static void clear() {
        query = "";
        type = "";
    }
}
