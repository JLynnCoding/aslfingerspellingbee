/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 5
 * Class that manages the "ASL Sink or Sign" feature of the app.
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class SinkOrSignActivity extends AppCompatActivity {

    private String TAG = "Track";

    private SpellingWords spellingWords;
    private String correctWord;
    private String spellingWord;

    private ImageView letterGraphic1, letterGraphic2, letterGraphic3, letterGraphic4,
            letterGraphic5, letterGraphic6, letterGraphic7, letterGraphic8, letterGraphic9,
            letterGraphic10, letterGraphic11, letterGraphic12, letterGraphic13;

    int wrongGuesses;
    int replacedLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sink_or_sign);

        spellingWords = new SpellingWords(this,"sinkOrSign");

        letterGraphic1 = (ImageView) findViewById(R.id.letterGraphic1);
        letterGraphic2 = (ImageView) findViewById(R.id.letterGraphic2);
        letterGraphic3 = (ImageView) findViewById(R.id.letterGraphic3);
        letterGraphic4 = (ImageView) findViewById(R.id.letterGraphic4);
        letterGraphic5 = (ImageView) findViewById(R.id.letterGraphic5);
        letterGraphic6 = (ImageView) findViewById(R.id.letterGraphic6);
        letterGraphic7 = (ImageView) findViewById(R.id.letterGraphic7);
        letterGraphic8 = (ImageView) findViewById(R.id.letterGraphic8);
        letterGraphic9 = (ImageView) findViewById(R.id.letterGraphic9);
        letterGraphic10 = (ImageView) findViewById(R.id.letterGraphic10);
        letterGraphic11 = (ImageView) findViewById(R.id.letterGraphic11);
        letterGraphic12 = (ImageView) findViewById(R.id.letterGraphic12);
        letterGraphic13 = (ImageView) findViewById(R.id.letterGraphic13);

        letterGraphic1.setVisibility(View.INVISIBLE);
        letterGraphic2.setVisibility(View.INVISIBLE);
        letterGraphic3.setVisibility(View.INVISIBLE);
        letterGraphic4.setVisibility(View.INVISIBLE);
        letterGraphic5.setVisibility(View.INVISIBLE);
        letterGraphic6.setVisibility(View.INVISIBLE);
        letterGraphic7.setVisibility(View.INVISIBLE);
        letterGraphic8.setVisibility(View.INVISIBLE);
        letterGraphic9.setVisibility(View.INVISIBLE);
        letterGraphic10.setVisibility(View.INVISIBLE);
        letterGraphic11.setVisibility(View.INVISIBLE);
        letterGraphic12.setVisibility(View.INVISIBLE);
        letterGraphic13.setVisibility(View.INVISIBLE);

        wrongGuesses = 0;
        replacedLetters = 0;

        getWord();
        setUpWord();
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
    public void setUpWord() {
        for(int i = 0; i < spellingWord.length(); i++) {
            if (i == 0){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic1.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic1.setVisibility(View.VISIBLE);
            } else if (i == 1){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic2.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic2.setVisibility(View.VISIBLE);
            } else if (i == 2){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic3.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic3.setVisibility(View.VISIBLE);
            } else if (i == 3){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic4.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic4.setVisibility(View.VISIBLE);
            } else if (i == 4){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic5.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic5.setVisibility(View.VISIBLE);
            } else if (i == 5){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic6.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic6.setVisibility(View.VISIBLE);
            } else if (i == 6){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic7.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic7.setVisibility(View.VISIBLE);
            } else if (i == 7){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic8.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic8.setVisibility(View.VISIBLE);
            } else if (i == 8){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic9.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic9.setVisibility(View.VISIBLE);
            } else if (i == 9){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic10.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic10.setVisibility(View.VISIBLE);
            } else if (i == 10){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic11.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic11.setVisibility(View.VISIBLE);
            } else if (i == 11){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic12.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic12.setVisibility(View.VISIBLE);
            } else if (i == 12){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic13.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                }
                letterGraphic13.setVisibility(View.VISIBLE);
            }
        }
    }

}