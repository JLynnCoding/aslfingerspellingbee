/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 2
 * Activity for ASL Beginner Spelling Bee.
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BeginnerSpellingBeeActivity extends AppCompatActivity {

    // Variables for Game Stats
    private SharedPreferences sharedPreferences;
    private int gamesWon;
    private int gamesLost;

    // Images for fingerspelled words.
    private ImageView letterGraphic1, letterGraphic2, letterGraphic3, letterGraphic4,
            letterGraphic5, letterGraphic6, letterGraphic7;
    //Instance of Words to get spelling words and helper variables.
    private SpellingWords words;
    private String spellingWord;
    private String correctWord;
    //Buttons and other changing layout elements.
    private EditText wordGuessText;
    private Button submitButton, nextWordButton;
    private TextView checkGuessDisplay;
    private InputMethodManager imm;

    /**
     * Creates Fingerspelling Bee, sets up activity, and gets and sets a word.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beginner_spelling_bee);
        wordGuessText = (EditText) findViewById(R.id.guessWord);
        submitButton = (Button) findViewById(R.id.submitButton);
        nextWordButton = (Button) findViewById(R.id.nextWordButton);
        nextWordButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay = (TextView) findViewById(R.id.checkAnswerText);
        checkGuessDisplay.setVisibility(View.INVISIBLE);

        letterGraphic1 = (ImageView) findViewById(R.id.letterGraphic1);
        letterGraphic2 = (ImageView) findViewById(R.id.letterGraphic2);
        letterGraphic3 = (ImageView) findViewById(R.id.letterGraphic3);
        letterGraphic4 = (ImageView) findViewById(R.id.letterGraphic4);
        letterGraphic5 = (ImageView) findViewById(R.id.letterGraphic5);
        letterGraphic6 = (ImageView) findViewById(R.id.letterGraphic6);
        letterGraphic7 = (ImageView) findViewById(R.id.letterGraphic7);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        sharedPreferences = getSharedPreferences("Stats", Context.MODE_PRIVATE);

        wordGuessText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    onClickSubmit(wordGuessText);

                    return true;
                }
                return false;
            }
        });

        words = new SpellingWords(this);

        if (savedInstanceState != null) {
            correctWord = savedInstanceState.getString("word", words.getRandomWord());
        } else {
            getWord();
        }

        setWord();
    }

    /**
     * Saves retrieved word to recreate activity if the orientation changes or the activity is
     * otherwise destroyed mid-game.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("word", correctWord);
    }

    /**
     * Gets a random word from the SpellingWords helper class.  Stores a copy as-is and
     * in all lowercase letters for easier access to graphics.
     */
    public void getWord() {
        correctWord = words.getRandomWord();
    }

    /**
     * Sets the fingerspelled word at the top of the Fingerspelling Bee Screen.
     */
    public void setWord() {
        spellingWord = correctWord.toLowerCase();

        // Sets first 3 hand signs because all words are 3 or more letters.
        letterGraphic1.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(0), null, getPackageName()));
        letterGraphic1.setContentDescription("" + spellingWord.charAt(0));
        letterGraphic2.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(1), null, getPackageName()));
        letterGraphic2.setContentDescription("" + spellingWord.charAt(1));
        letterGraphic3.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(2), null, getPackageName()));
        letterGraphic3.setContentDescription("" + spellingWord.charAt(2));

        // Checks if word is longer than 3 letters up to maximum, and sets hand signs appropriately.
        if (spellingWord.length()>3){
            letterGraphic4.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(3), null, getPackageName()));
            letterGraphic4.setContentDescription("" + spellingWord.charAt(3));
            letterGraphic4.setVisibility(View.VISIBLE);
        } else {
            letterGraphic4.setVisibility(View.INVISIBLE);
        }
        if (spellingWord.length()>4){
            letterGraphic5.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(4), null, getPackageName()));
            letterGraphic5.setContentDescription("" + spellingWord.charAt(4));
            letterGraphic5.setVisibility(View.VISIBLE);
        } else {
            letterGraphic5.setVisibility(View.INVISIBLE);
        }
        if (spellingWord.length()>5){
            letterGraphic6.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(5), null, getPackageName()));
            letterGraphic6.setContentDescription("" + spellingWord.charAt(5));
            letterGraphic6.setVisibility(View.VISIBLE);
        } else {
            letterGraphic6.setVisibility(View.INVISIBLE);
        }
        if (spellingWord.length()>6){
            letterGraphic7.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(6), null, getPackageName()));
            letterGraphic7.setContentDescription("" + spellingWord.charAt(6));
            letterGraphic7.setVisibility(View.VISIBLE);
        } else {
            letterGraphic7.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Check user's typed answer upon their click of the "Submit" button and displays results.
     */
    public void onClickSubmit(View view) {
        gamesWon = sharedPreferences.getInt("gamesWonBeginnerSpellingBee", 0);
        gamesLost = sharedPreferences.getInt("gamesLostBeginnerSpellingBee", 0);

        String guess = wordGuessText.getText().toString().toLowerCase();
        wordGuessText.setEnabled(false);

        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

        if(guess.equals(spellingWord)) {
            checkGuessDisplay.setText("Correct!");
            checkGuessDisplay.setBackgroundColor(-16711936);
            checkGuessDisplay.setTextColor(-16777216);
            gamesWon++;
            sharedPrefEditor.putInt("gamesWonBeginnerSpellingBee", gamesWon);
        } else {
            checkGuessDisplay.setText("Incorrect. The correct answer is " + correctWord + ".");
            checkGuessDisplay.setBackgroundColor(-65536);
            checkGuessDisplay.setTextColor(-1);
            gamesLost++;
            sharedPrefEditor.putInt("gamesLostBeginnerSpellingBee", gamesLost);
        }

        sharedPrefEditor.apply();

        submitButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.VISIBLE);
        nextWordButton.setVisibility(View.VISIBLE);
    }

    /**
     * Gets a new fingerspelled word upon the user clicking "Next Word".
     */
    public void onClickNextWord(View view) {
        nextWordButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        wordGuessText.setText("");
        wordGuessText.setEnabled(true);

        getWord();
        setWord();
    }



}
