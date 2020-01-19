package com.pioterDeveloper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentFinder extends Content{

    private ArrayList<Content> imageBase = new ArrayList<Content>();
    //Pattern title_regex = Pattern.compile("title=\"[a-zA-Z0-9\\s]+");
    //Pattern img_url_regex = Pattern.compile("(http|https):\\/\\/([a-zA-Z0-9]+.[a-z]+|www.[a-zA-Z0-9]+.[a-z]+)\\/(?:[:a-zA-Z0-9\\s\\/.:_-]*)[.]png|jpg|svg|gif|jpeg|swf");
    private String webPage;
    private OpenFile open = new OpenFile();

    public void loadPageContent(String fileName){
        open.openFIle(fileName);
        webPage = open.getContent();
    }
    public void findImgae(){

        Matcher title_matcher = Pattern.compile("title=\\\"[a-zA-Z0-9\\s-\"]+>").matcher(webPage);
        Matcher url_matcher = Pattern.compile("(?:[:a-zA-Z0-9\\s\\/.:_-]*)[.](png|jpg|svg|gif|jpeg|swf)").matcher(webPage);
        //(http|https):\/\/([a-zA-Z0-9]+.[a-z]+|www.[a-zA-Z0-9]+.[a-z]+)\/(?:[:a-zA-Z0-9\s\/.:_-]*)[.](png|jpg|svg|gif|jpeg|swf)
        //((http|https):\/\/([a-zA-Z0-9]+.[a-z]+|www.[a-zA-Z0-9]+.[a-z]+)\/(?:[:a-zA-Z0-9\s\/.:_-]*)[.](png|jpg|svg|gif|jpeg|swf))|\/\/.*(\.(png|jpg|svg|gif|jpeg|swf))



        while(title_matcher.find() && url_matcher.find()) {
            Content temp = new Content();
            temp.setTitle(title_matcher.group(0).replaceAll("title=\"", "").replaceAll("\">",""));
            temp.setImg_url(url_matcher.group(0));
            imageBase.add(temp);
        }
    }


    public ArrayList<Content> getImageBase() {
        return imageBase;
    }
}
