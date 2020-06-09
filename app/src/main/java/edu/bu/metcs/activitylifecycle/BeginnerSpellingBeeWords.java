/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 2
 * Class that creates and manages ArrayList of Beginner Fingerspelling Bee Words and provides data
 * needed for app features. Reads list of words from files.
 */
package edu.bu.metcs.activitylifecycle;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class BeginnerSpellingBeeWords {

    private String TAG = "logInfo";

    ArrayList<String> beginnerSpellingBeeWords;
    private Context context; //TRYING

    /**
     * Loads BeginnerSpellingBeeWords into ArrayList for easy reference and randomizing order for
     * use in Beginner Fingerspelling Bee.
     */
    public BeginnerSpellingBeeWords(Context context) {
        beginnerSpellingBeeWords = new ArrayList<>();
        this.context = context;

        ReadFromFileUsingScanner();

        Log.i(TAG, "Spellingbee loaded");
    }

    /**
     * Opens file, reads it by line, and adds each line as a String into the ArrayList of
     * Beginner Spelling Bee Words.
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

            Log.i(TAG, "There are " + beginnerSpellingBeeWords.size() + "words in the " +
                    "spelling bee");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a random word from the list.
     */
    public String getRandomWord() {
        Random randomNumber = new Random();
        return beginnerSpellingBeeWords.get(randomNumber.nextInt(beginnerSpellingBeeWords.size()));
    }

}
