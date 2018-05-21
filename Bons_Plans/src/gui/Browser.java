///*
// * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions
// * are met:
// *
// *   - Redistributions of source code must retain the above copyright
// *     notice, this list of conditions and the following disclaimer.
// *
// *   - Redistributions in binary form must reproduce the above copyright
// *     notice, this list of conditions and the following disclaimer in the
// *     documentation and/or other materials provided with the distribution.
// *
// *   - Neither the name of Oracle or the names of its
// *     contributors may be used to endorse or promote products derived
// *     from this software without specific prior written permission.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
// * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
// * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
// * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
// * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
// * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
// * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
// * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
// * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// */
//package gui;
//
//
//import Facebook.Configuration;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.Point;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Platform;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import static javafx.concurrent.Worker.State.FAILED;
//import javafx.embed.swing.JFXPanel;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebEvent;
//import javafx.scene.web.WebView;
//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
//
//public class Browser extends JFrame implements Runnable{
//    
//    private static final long serialVersionUID = 1L;
//
//    private final JFXPanel jfxPanel = new JFXPanel();
//    private static WebEngine engine;
//
//    private final JPanel panel = new JPanel(new BorderLayout());
//
//    public Browser() {
//        super();
//        setTitle("Loading PLease Wait....");
//        new Thread(this).start();
//        initComponents();
//    }
//
//    private void initComponents() {
//        createScene();
//
//        JPanel topBar = new JPanel(new BorderLayout(5, 0));
//        topBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
//
//        JPanel statusBar = new JPanel(new BorderLayout(5, 0));
//        statusBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
//
//        panel.add(topBar, BorderLayout.NORTH);
//        panel.add(jfxPanel, BorderLayout.CENTER);
//        panel.add(statusBar, BorderLayout.SOUTH);
//
//        getContentPane().add(panel);
//
//        setPreferredSize(new Dimension(1024, 600));
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        pack();
//    }
//
//    private void createScene() {
//
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//
//                WebView view = new WebView();
//                engine = view.getEngine();
//
//                engine.titleProperty().addListener(new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
//                        SwingUtilities.invokeLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                Browser.this.setTitle(newValue);
//                            }
//                        });
//                    }
//                });
//
//                engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
//                    @Override
//                    public void handle(final WebEvent<String> event) {
//                        SwingUtilities.invokeLater(new Runnable() {
//                            @Override
//                            public void run() {
//                            }
//                        });
//                    }
//                });
//                engine.getLoadWorker()
//                        .exceptionProperty()
//                        .addListener(new ChangeListener<Throwable>() {
//
//                            @Override
//                            public void changed(ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) {
//                                if (engine.getLoadWorker().getState() == FAILED) {
//                                    SwingUtilities.invokeLater(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            JOptionPane.showMessageDialog(
//                                                    panel,
//                                                    (value != null)
//                                                    ? engine.getLocation() + "\n" + value.getMessage()
//                                                    : engine.getLocation() + "\nUnexpected error.",
//                                                    "Loading error...",
//                                                    JOptionPane.ERROR_MESSAGE);
//                                        }
//                                    });
//                                }
//                            }
//                        });
//                jfxPanel.setScene(new Scene(view));
//            }
//        });
//    }
//
//    public void loadURL(final String url) {
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                String tmp = toURL(url);
//
//                if (tmp == null) {
//                    tmp = toURL("http://" + url);
//                }
//                engine.load(tmp);
//            }
//        });
//    }
//
//    private static String toURL(String str) {
//        try {
//            return new URL(str).toExternalForm();
//        } catch (MalformedURLException exception) {
//            return null;
//        }
//    }
//
//    public static void setUrl(final String url) {
//        Platform.runLater(new Runnable() {
//
//            @Override
//            public void run() {
//                engine.load(toURL(url));
//            }
//        });
//    }
//    
//    public static void load(final String url, final Dimension size, final Point loc) {
//        //</editor-fold>
//        SwingUtilities.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                Browser browser = new Browser();
//                browser.setSize(size);
//                browser.setLocation(loc);
//                browser.setVisible(true);
//                browser.loadURL(url);
//            }
//        });
//    }
//    
//    public static void load(final String url, final Boolean fromFacebook) {
//        //</editor-fold>
//        SwingUtilities.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                Browser browser = new Browser();
//                browser.setVisible(true);
//                browser.loadURL(url);
//            }
//        });
//    }
//    
//   // public static void main(String[] argv) {
//     //   Browser.load("www.facebook.com", new Dimension(300, 300), new Point(30, 20));
//    //}
//
//    @Override
//    public void run() {
//        Boolean run = true;
//        while(run) {
//            try {
//                Thread.sleep(200);
//                if(engine.getDocument().getDocumentURI().contains("code=")) {
//                    setVisible(false);
//                    run = false;
//                    Configuration.token = engine.getDocument().getDocumentURI().split("code=")[1];
//                    Configuration.accessToken = Facebook.Facebook.getOAuthToken();
//                    if(Facebook.Facebook.isSerialize()) {
//                        Facebook.Facebook.serializeToken(Facebook.Facebook.getSerializeToFile());
//                    }
//                    dispose();
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
//            } catch(Exception e) {
//                
//            }
//        }
//    }
//}
