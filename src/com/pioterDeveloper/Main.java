package com.pioterDeveloper;




import org.apache.commons.io.IOUtils;
import org.junit.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class Main {

    public static void main(String[] args) {

        ArrayList<Content> content = new ArrayList<Content>();

        ContentFinder finder = new ContentFinder();

        finder.loadPageContent("cats.html");
        finder.findImgae();
        content = finder.getImageBase();

        int counter = 1;
        for(Content c: content){
            System.out.println(counter + ". Image title: " + c.title);
            System.out.println("Image URL: " + c.img_url + "\n");
            counter++;
        }


        imageDownloader(content);

    }


    public static void webpageToFile(String url) throws IOException {
        URL website = new URL("http://www.cutestpaw.com/wp-content/uploads/2011/11/Kitty-Coat.jpg");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("exampleCat.jpg");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    public static void urlToImage(String url, String fileName) throws IOException {

        URL website = new URL(url);

        if(website.openConnection() != null) {
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("downloads/" + fileName + ".jpg");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        else{
            System.out.println("Connection error: " + url);
        }

    }

    public static void imageDownloader(ArrayList<Content> content){

        try{

            for(Content c: content) {
                URL tmp = new URL(c.img_url);
                HttpURLConnection huc = (HttpURLConnection) tmp.openConnection();
                int responseCode = huc.getResponseCode();
                //Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
                if(responseCode == 200){
                    urlToImage(c.img_url, c.title);
                }
                else
                {
                    System.out.println("Response code: " + responseCode + " - " + huc.getResponseMessage() + " File: " + "\"" + c.title + "\"");
                }
            }
        }
        catch(IOException e){
           // System.out.println(e.printStackTrace());
            e.printStackTrace();
        }
    }

   /* public  static void downloader(ArrayList<Content> content){
        ReadableByteChannel rbc = null;
        int i = 0;
        try{
            for(i = 0; i < content.size(); i++){
                URL website = new URL(content.get(i).img_url);
                rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream(content.get(i).title + ".jpg");
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        }
        catch(IOException e){
            System.out.println(e.getCause());
            i++;
        }
        finally {
            try{
                rbc.close();
                URL website = new URL(content.get(i).img_url);
                rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream(content.get(i).title + ".jpg");
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            }catch(IOException e){
                System.out.println(e.getCause());
            }
        }

    }*/
}
