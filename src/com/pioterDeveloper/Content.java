package com.pioterDeveloper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Content {

    protected String url;
    protected String title;
    protected String img_url;
    protected String kindOfProtocol;

    Content(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        if(findProtocol(img_url) == true){
            this.img_url = img_url;
        }
        else{
            String tmp = "http:";
            this.img_url = tmp + img_url;
            System.out.println(img_url);
        }

    }

    protected boolean findProtocol(String content){
        Matcher title_matcher = Pattern.compile("http").matcher(content);
        if(title_matcher.find()){
            this.kindOfProtocol = "http";
            return true;
        }
        else{
            this.kindOfProtocol = "";
            return false;
        }
    }

    public String getKindOfProtocol() {
        return kindOfProtocol;
    }
}
