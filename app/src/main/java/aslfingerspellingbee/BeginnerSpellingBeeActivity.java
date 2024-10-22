/**
 * ASL Fingerspelling Bee
 * Activity for ASL Beginner Spelling Bee, which displays a 3-7 letter word as hand signs from
 * the American Manual Alphabet and asks the user to guess the word. Added onKeyListener,
 * savedInstanceState, and statistics to be stored in SharedPreferences.
 */

package aslfingerspellingbee;

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

        // Sets up Views for reference
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

        // Variable for soft keyboard
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        // Variable for SharedPreferences for Statistics storage
        sharedPreferences = getSharedPreferences("Stats", Context.MODE_PRIVATE);

        /*
         * onKeyListener() to detect if user pressed "Enter" and advances to onClickSubmit.
         */
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

        // Sets up Spelling Words and gets a new word or the previous retrieved word from
        // SavedInstanceState, if the activity was interrupted.
        words = new SpellingWords(this);
        if (savedInstanceState != null) {
            correctWord = savedInstanceState.getString("word", words.getRandomWord());
        } else {
            getWord();
        }
        setWord(); // Sets up word display
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
     * Gets a random word from the SpellingWords helper class.
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
        letterGraphic1.setImageResource(getResources().getIdentifier("@drawable/" +
                spellingWord.charAt(0), null, getPackageName()));
        letterGraphic1.setContentDescription("" + spellingWord.charAt(0));
        letterGraphic2.setImageResource(getResources().getIdentifier("@drawable/" +
                spellingWord.charAt(1), null, getPackageName()));
        letterGraphic2.setContentDescription("" + spellingWord.charAt(1));
        letterGraphic3.setImageResource(getResources().getIdentifier("@drawable/" +
                spellingWord.charAt(2), null, getPackageName()));
        letterGraphic3.setContentDescription("" + spellingWord.charAt(2));

        // Checks if word is longer than 3 letters up to maximum, and sets hand signs appropriately.
        if (spellingWord.length()>3){
            letterGraphic4.setImageResource(getResources().getIdentifier("@drawable/" +
                    spellingWord.charAt(3), null, getPackageName()));
            letterGraphic4.setContentDescription("" + spellingWord.charAt(3));
            letterGraphic4.setVisibility(View.VISIBLE);
        } else {
            letterGraphic4.setVisibility(View.GONE);
        }
        if (spellingWord.length()>4){
            letterGraphic5.setImageResource(getResources().getIdentifier("@drawable/" +
                    spellingWord.charAt(4), null, getPackageName()));
            letterGraphic5.setContentDescription("" + spellingWord.charAt(4));
            letterGraphic5.setVisibility(View.VISIBLE);
        } else {
            letterGraphic5.setVisibility(View.GONE);
        }
        if (spellingWord.length()>5){
            letterGraphic6.setImageResource(getResources().getIdentifier("@drawable/" +
                    spellingWord.charAt(5), null, getPackageName()));
            letterGraphic6.setContentDescription("" + spellingWord.charAt(5));
            letterGraphic6.setVisibility(View.VISIBLE);
        } else {
            letterGraphic6.setVisibility(View.GONE);
        }
        if (spellingWord.length()>6){
            letterGraphic7.setImageResource(getResources().getIdentifier("@drawable/" +
                    spellingWord.charAt(6), null, getPackageName()));
            letterGraphic7.setContentDescription("" + spellingWord.charAt(6));
            letterGraphic7.setVisibility(View.VISIBLE);
        } else {
            letterGraphic7.setVisibility(View.GONE);
        }
    }

    /**
     * Check user's typed answer upon their click of the "Submit" button and displays results.
     * Updates statistics in SharedPreferences.
     */
    public void onClickSubmit(View view) {
        // Gets existing statistics from SharedPreferences and stores locally to update
        gamesWon = sharedPreferences.getInt("gamesWonBeginnerSpellingBee", 0);
        gamesLost = sharedPreferences.getInt("gamesLostBeginnerSpellingBee", 0);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

        String guess = wordGuessText.getText().toString().toLowerCase();
        wordGuessText.setEnabled(false);

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
