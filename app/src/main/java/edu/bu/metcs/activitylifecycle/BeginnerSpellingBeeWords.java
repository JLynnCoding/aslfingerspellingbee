/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 2
 * Class that creates and manages ArrayList for Beginner Fingerspelling Bee Words and provides data
 * needed for app features.
 */
package edu.bu.metcs.activitylifecycle;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BeginnerSpellingBeeWords {

    private String TAG = "logInfo";

    ArrayList<String> beginnerSpellingBeeWords;
    Scanner reader;
    private Context context; //TRYING

    /**
     * Loads BeginnerSpellingBeeWords in ArrayList for easy reference and randomizing order for use
     * in Beginner Fingerspelling Bee.
     */
    public BeginnerSpellingBeeWords(Context context) {
        beginnerSpellingBeeWords = new ArrayList<>();
        this.context = context;

        ReadFromFileUsingScanner();

        Log.i(TAG, "Spellingbee loaded");
    }

    /**
     * Opens
     */
    private void ReadFromFileUsingScanner() {

        AssetManager am = context.getAssets();

        try {
            InputStream is = am.open("beginnerspellingbeepropernouns.txt");
            Log.i(TAG, "File opened.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                beginnerSpellingBeeWords.add(line);
            }

            Log.i(TAG, "There are " + beginnerSpellingBeeWords.size() + "words in the spelling bee");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Random word getting for future features.
     */
    public String getRandomWord() {
        Random randomNumber = new Random();
        return beginnerSpellingBeeWords.get(randomNumber.nextInt(beginnerSpellingBeeWords.size()));
    }

}
