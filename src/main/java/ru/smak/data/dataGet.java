package ru.smak.data;

import com.google.gson.Gson;

import java.io.FileReader;

public class dataGet
{
    public static String pathDontName;
    public Root parser()
    {
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(pathDontName)) {
            Root Root = gson.fromJson(reader,Root.class);
            return Root;
        }
        catch (Exception e)
        {
            System.out.println("Parsing error" + e.toString());
        }
        return null;
    }
}
