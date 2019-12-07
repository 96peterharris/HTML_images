package com.pioterDeveloper;




import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;




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
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(fileName +".jpg");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

    }

    public static void imageDownloader(ArrayList<Content> content){

        try{

            for(Content c: content) {
                urlToImage(c.img_url, c.title);
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
