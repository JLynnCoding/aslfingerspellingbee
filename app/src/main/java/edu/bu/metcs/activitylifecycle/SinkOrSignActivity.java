/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 5
 * Class that manages the "ASL Sink or Sign" feature of the app.
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SinkOrSignActivity extends AppCompatActivity {

    private String TAG = "Track";

    private SpellingWords spellingWords;
    private String correctWord;
    private String spellingWord;

    int wrongGuesses = 0;
    int replacedLetters = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sink_or_sign);

        spellingWords = new SpellingWords(this,"sinkOrSign");

        getWord();
    }

    /**
     * Gets a random word from the SpellingWords helper class. Stores a copy as-is and
     * in all lowercase letters for easier access to graphics.
     */
    public void getWord() {
        correctWord = spellingWords.getRandomWord();
        spellingWord = correctWord.toLowerCase();
        Log.i(TAG, "Retrieved word: " + correctWord);
    }

    /**
     * Sets up images for word and updates as needed.
     */

}