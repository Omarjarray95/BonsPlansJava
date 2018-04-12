/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facebook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Tamojit9
 */
public class Photo {

    private static int limit = 50;
    public static String TYPE_TAGGED = "tagged", TYPE_UPLOADED = "uploaded";
    private static String type = "";

    public static void setType(String type) {
        type = type;
    }

    public static String getType() {
        return type;
    }

    public static void setLimit(int limit) {
        Photo.limit = limit;
    }

    public static int getLimit() {
        return limit;
    }

    public static Iterator<URL> getURLIterator() throws Exception {
        return new Iterator<URL>() {
            int index = 0;
            JsonObject photos = new GraphAPI().GET("me/photos").fields("source").AND("type").value(type).build();
            JsonArray photoArray = photos.get("data").getAsJsonArray();

            @Override
            public boolean hasNext() {
                //System.out.println(index + " " + photoArray.size());
                if (index >= photoArray.size()) {
                    try {
                        GraphAPI.clear();
                        photos = new GraphAPI().GET("me/photos").fields("source").
                                AND("limit").value(Integer.toString(limit)).AND("until").value(getUntil()).
                                AND("type").value(type).build();
                        photoArray = photos.get("data").getAsJsonArray();
                        photoArray.size();
                        index = 0;
                        //System.out.println(photos);
                        //System.out.println(photoArray.size());
                        return photoArray.size() > 0;
                    } catch (Exception ex) {
                        Logger.getLogger(Photo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return index < photoArray.size();
            }

            String getUntil() {
                return photos.get("paging").getAsJsonObject().get("next").getAsString().split("until=")[1];
            }

            @Override
            public URL next() {
                //System.out.println("Enters next");
                try {
                  //  System.out.println("enters try");
                    URL url = new URL(photoArray.get(index++).getAsJsonObject().get("source").getAsString());
                   // System.out.println("exits normal next");
                    //System.out.println(url);
                    return url;
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Photo.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("Exits NULL");
                return null;
            }

            @Override
            public void remove() {
            }
        };
    }

    public static Iterator<BufferedImage> getBufferedImageIterator() throws Exception {
        return new Iterator<BufferedImage>() {
            Iterator<URL> it = getURLIterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public BufferedImage next() {
                try {
                    return ImageIO.read(it.next());
                } catch (IOException ex) {
                    Logger.getLogger(Photo.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;

            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
}
