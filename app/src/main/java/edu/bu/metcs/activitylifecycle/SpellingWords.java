/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 2
 * Class that creates and manages ArrayList of Beginner Fingerspelling Bee Words and provides data
 * needed for app features. Reads list of words from files.
 * Project Iteration 5: Updated to work for Sink or Sign activity as well.
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

public class SpellingWords {

    private String TAG = "logInfo";

    ArrayList<String> spellingList;
    private Context context;
    private String type;
    InputStream is;

    /**
     * Loads SpellingWords into ArrayList for easy reference and randomizing order for
     * use in various app features.
     */
    public SpellingWords(Context context) {
        spellingList = new ArrayList<>();
        this.context = context;

        type = "";

        ReadFromFileUsingScanner();

        Log.i(TAG, "SpellingWords loaded");
    }

    public SpellingWords(Context context, String type){
        spellingList = new ArrayList<>();
        this.context = context;

        this.type = type;

        ReadFromFileUsingScanner();

        Log.i(TAG, "Word helper loaded");
    }

    /**
     * Opens file, reads it by line, and adds each line as a String into the ArrayList of
     * Beginner Spelling Bee Words.
     */
    private void ReadFromFileUsingScanner() {

        AssetManager am = context.getAssets();
        try {
            if (type.equals("sinkOrSign")){
                is = am.open("advancedspellingwords.txt");
                Log.i(TAG, "File opened.");
            } else {
                is = am.open("beginnerspellingbeepropernouns.txt");
                Log.i(TAG, "File opened.");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                spellingList.add(line);
            }

            Log.i(TAG, "There are " + spellingList.size() + "words in the " +
                    "spelling list");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a random word from the list.
     */
    public String getRandomWord() {
        Random randomNumber = new Random();
        return spellingList.get(randomNumber.nextInt(spellingList.size()));
    }

}