package com.pioterDeveloper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenFile{

    OpenFile(){};

    String reader;
    String content;

    public void openFIle(String fileName){

        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName)); //"cats.html"

            while ((reader = input.readLine()) != null) {
                contentBuilder.append(reader);
            }
            input.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        content = contentBuilder.toString();

    }

    public String getContent(){
        return this.content;
    }
}
