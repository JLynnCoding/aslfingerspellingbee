/**
 *  Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 3
 *  Class for the ASL Letter Quiz Activity.
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LetterQuizActivity extends AppCompatActivity {

    private  Alphabet alphabet;  // Loads class that manages English alphabet
    private ImageView handSignGraphic; // Holds graphic for hand sign
    private EditText guessLetter;
    private Button submitButton, nextButton; // Button objects for advancing
    private TextView checkGuessDisplay;
    private String letter;
    private String displayLetter;


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

        handSignGraphic.setImageResource(getResources().getIdentifier("@drawable/" +
                letter, null, getPackageName()));
        handSignGraphic.setContentDescription("" + letter);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("letter", alphabet.getCurrentLetter());
        savedInstanceState.putString("displayLetter", alphabet.getCurrentLetterDisplay());
    }

    /**
     * Check user's typed answer upon their click of the "Submit" button and displays results.
     */
    public void onClickSubmit(View view) {
        String guess = guessLetter.getText().toString().toLowerCase();

        if(guess.equals("" + displayLetter.charAt(1))) {
            checkGuessDisplay.setText("Correct!");
            checkGuessDisplay.setBackgroundColor(-16711936);
            checkGuessDisplay.setTextColor(-16777216);
        } else {
            checkGuessDisplay.setText("Incorrect. The correct letter is " + displayLetter + ".");
            checkGuessDisplay.setBackgroundColor(-65536);
            checkGuessDisplay.setTextColor(-1);
        }
        submitButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
    }

    /**
     * Gets a new handsign upon the user clicking the "Next" button.
     */
    public void onClickNext(View view) {
        nextButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        guessLetter.setText("");
        alphabet.getNextLetter();
        setUpQuiz();
    }
}