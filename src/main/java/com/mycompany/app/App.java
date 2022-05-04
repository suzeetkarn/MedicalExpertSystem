package com.mycompany.app;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App
{
    @SuppressWarnings("unchecked")
    public static void main( String[] args )
    {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        System.out.print("Enter your symptoms : ");
        String str= sc.nextLine();
        String[] words = str.split("\\s+");
        for (int i = 0; i < words.length; i++) {
                // You may want to check for a non-word character before blindly
                // performing a replacement
                // It may also be necessary to adjust the character class
                words[i] = words[i].replaceAll("[^\\w]", "");
        }
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("/src/assets/data.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray covidReport = (JSONArray) obj;
            int size = covidReport.size();
            Object[] stringArray = covidReport.toArray(new String[size]);

            for(int i=0;i<words.length;i++)
            {
                for(int j=0;j<size ;j++){
                    if(words[i] == stringArray[j]){
                        System.out.println("you are at Covid Risk");

                    }
                    else{
                        System.out.println("You are covid Risk free");

                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
