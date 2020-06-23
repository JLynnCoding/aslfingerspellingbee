/**
 *  Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 3
 *  Class for the ASL Letter Quiz Activity.
 *  Update Project Iteration 5
 *  Added Statistics for storage in SharedPreferences and SavedInstanceState to allow for seamless
 *  recreation of the activity. Added onKeyListener to advance to onClickSubmit when user hits
 *  "Enter" key. d
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LetterQuizActivity extends AppCompatActivity {

    // Variables for Game Stats
    private SharedPreferences sharedPreferences;
    private int gamesWon;
    private int gamesLost;

    private  Alphabet alphabet;  // Loads class that manages English alphabet
    private ImageView handSignGraphic; // Holds graphic for hand sign
    private EditText guessLetter;
    private Button submitButton, nextButton; // Button objects for advancing
    private TextView checkGuessDisplay;
    private String letter;
    private String displayLetter;
    private InputMethodManager imm;

    /**
     * Sets up variables for the layout and activity for the ASL Letter Quiz.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_quiz);

        // Sets up alphabet and shuffles it to display the letters in a random order.
        alphabet = new Alphabet();
        alphabet.shuffleLetters();

        // Initializes parts of the layout.
        handSignGraphic = (ImageView) findViewById(R.id.handsignGraphic);
        guessLetter = (EditText) findViewById(R.id.guessLetter);
        displayLetter = "";
        submitButton = (Button) findViewById(R.id.submitButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        submitButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay = (TextView) findViewById(R.id.checkAnswerText);
        checkGuessDisplay.setVisibility(View.INVISIBLE);

        sharedPreferences = getSharedPreferences("Stats", Context.MODE_PRIVATE);

        // Initializes soft keyboard variable.
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        // Hides soft keyboard and advances to onClickSubmit after "Enter" key is used
        guessLetter.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    onClickSubmit(guessLetter);
                    return true;
                }
                return false;
            }
        });

        // Gets new word if none is already stored from the app changing orientations or being
        // otherwise destroyed and recreated.
        if (savedInstanceState != null) {
            letter = savedInstanceState.getString("letter", alphabet.getCurrentLetter());
            displayLetter = savedInstanceState.getString("displayLetter",
                    alphabet.getCurrentLetterDisplay());
        } else {
            getLetter();
        }
        setUpQuiz();
    }

    /**
     * Get a word.
     */
    public void getLetter() {
        letter = alphabet.getCurrentLetter();
        displayLetter = alphabet.getLetterDisplay(letter);
    }

    /**
     * Sets up the quiz layout with a new handsign.
     */
    public void setUpQuiz() {
        Log.i("Track", "Set up started");
        handSignGraphic.setImageResource(getResources().getIdentifier("@drawable/" +
                letter, null, getPackageName()));
        handSignGraphic.setContentDescription("" + letter);
    }

    /**
     * Saves retrieved word to recreate activity if the orientation changes or the activity is
     * otherwise destroyed mid-game.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("letter", alphabet.getCurrentLetter());
        savedInstanceState.putString("displayLetter", alphabet.getCurrentLetterDisplay());
    }

    /**
     * Check user's typed answer upon their click of the "Submit" button, displays results, and
     * records the results to SharedPreferences.
     */
    public void onClickSubmit(View view) {
        String guess = guessLetter.getText().toString().toLowerCase();
        gamesWon = sharedPreferences.getInt("gamesWonLetterQuiz", 0);
        gamesLost = sharedPreferences.getInt("gamesLostLetterQuiz", 0);

        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

        if(guess.equals("" + displayLetter.charAt(1))) {
            checkGuessDisplay.setText("Correct!");
            checkGuessDisplay.setBackgroundColor(-16711936);
            checkGuessDisplay.setTextColor(-16777216);
            gamesWon++;
            sharedPrefEditor.putInt("gamesWonLetterQuiz", gamesWon);
        } else {
            checkGuessDisplay.setText("Incorrect. The correct letter is " + displayLetter + ".");
            checkGuessDisplay.setBackgroundColor(-65536);
            checkGuessDisplay.setTextColor(-1);
            gamesLost++;
            sharedPrefEditor.putInt("gamesLostLetter", gamesLost);
        }
        sharedPrefEditor.apply();

        submitButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        guessLetter.setEnabled(false);
    }

    /**
     * Gets a new handsign upon the user clicking the "Next" button.
     */
    public void onClickNext(View view) {
        nextButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        guessLetter.setText("");
        guessLetter.setEnabled(true);
        alphabet.getNextLetter();
        Log.i("Track", alphabet.getCurrentLetter() + " is the letter");
        getLetter();
        setUpQuiz();
    }
}