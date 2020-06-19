/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 5
 * Class that manages the "ASL Sink or Sign" feature of the app.
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SinkOrSignActivity extends AppCompatActivity {

    private String TAG = "Track";

    // Variables for Word sourcing.
    private SpellingWords spellingWords;
    private String correctWord;
    private String spellingWord;

    // Variables to be linked to Views.
    private ImageView letterGraphic1, letterGraphic2, letterGraphic3, letterGraphic4,
            letterGraphic5, letterGraphic6, letterGraphic7, letterGraphic8, letterGraphic9,
            letterGraphic10, letterGraphic11, letterGraphic12, letterGraphic13, sharkAttack;
    private TextView gameProgressText;
    private Button nextButton;

    // Variables to track game progress.
    int wrongGuesses;
    int replacedLetters;
    boolean foundLetter;

    // Variable to track disabled Views.
    private ArrayList<View> disabledViews;

    /**
     * Initialized variables needed for the ASL Sink or Sign feature and calls the methods needed
     * to start the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sink_or_sign);

        spellingWords = new SpellingWords(this,"sinkOrSign");

        // Link letter graphic and shark graphic ImageViews to variables.
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
        sharkAttack = (ImageView) findViewById(R.id.sharkattack);

        // Link Views to variables that control game progress.
        gameProgressText = (TextView) findViewById(R.id.gameProgressText);
        gameProgressText.setVisibility(View.INVISIBLE);
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setVisibility(View.INVISIBLE);

        // Initialize variables to track game progress.
        wrongGuesses = 0;
        replacedLetters = 0;
        foundLetter = false;

        // Initialize ArrayList to track disabled views.
        disabledViews = new ArrayList<>();

        // Get and set up new word for activity.
        getWord();
        setUpWord();

        Log.i(TAG, "Sink or Sign has been set up.");
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
     * Sets up images for word and updates as needed. A space graphic is used to denote spaces in
     * the selected word and letters are shown as underscores. Any unused letter graphics remain
     * hidden to the user.
     */
    public void setUpWord() {

        // Resets all graphics to blank lines/underscores and makes them invisible.
        clearWord();

        // Checks the chosen word for spaces and dynamically assigns the space image and
        // description. Tracks how many letters are replaced.
        for(int i = 0; i < spellingWord.length(); i++) {
            if (i == 0){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic1.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic1.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic1.setVisibility(View.VISIBLE);
                Log.i(TAG, "Set Visibility Graphic 1");
            } else if (i == 1){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic2.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic2.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic2.setVisibility(View.VISIBLE);
            } else if (i == 2){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic3.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic3.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic3.setVisibility(View.VISIBLE);
            } else if (i == 3){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic4.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic4.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic4.setVisibility(View.VISIBLE);
            } else if (i == 4){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic5.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic5.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic5.setVisibility(View.VISIBLE);
            } else if (i == 5){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic6.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic6.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic6.setVisibility(View.VISIBLE);
            } else if (i == 6){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic7.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic7.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic7.setVisibility(View.VISIBLE);
            } else if (i == 7){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic8.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic8.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic8.setVisibility(View.VISIBLE);
            } else if (i == 8){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic9.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic9.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic9.setVisibility(View.VISIBLE);
            } else if (i == 9){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic10.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic10.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic10.setVisibility(View.VISIBLE);
            } else if (i == 10){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic11.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic11.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic11.setVisibility(View.VISIBLE);
            } else if (i == 11){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic12.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic12.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic12.setVisibility(View.VISIBLE);
            } else if (i == 12){
                if(("" + spellingWord.charAt(i)).equals(" ")) {
                    letterGraphic13.setImageResource(getResources().getIdentifier(
                            "@drawable/space", null, getPackageName()));
                    letterGraphic13.setContentDescription("space");
                    replacedLetters++;
                }
                letterGraphic13.setVisibility(View.VISIBLE);
            }
        }
        Log.i(TAG, "Word was set up.");
        Log.i(TAG, replacedLetters + " letters were replaced.");
        Log.i(TAG, wrongGuesses + " wrong guesses.");
    }

    /**
     * A method that responds to touches individual letter hand signs and calls the appropriate
     * methods to check if the letter is in the selected word.
     */
    public void onClickSign(View view) {
        // Get the letter from the ContentDescription
        CharSequence letter = view.getContentDescription();
        Log.i(TAG, "The guessed letter is " + letter);
        Log.i(TAG, replacedLetters + " letters were replaced.");
        Log.i(TAG, wrongGuesses + " wrong guesses.");

        // Resets variable to false.
        foundLetter = false;

        // Disables letter hand sign so the user cannot select it again and adds it to
        // disabledViews to track it.
        view.setEnabled(false);
        disabledViews.add(view);

        // Calls method to check letter against the word
        checkWord(letter);
    }

    /**
     * Checks if the letter the user selected is in the word. If the letter is found, the
     * corresponding handsign image appears in the corresponding spot in the word, the
     * replacedLetters count is updated and the foundLetter boolean is set to true.
     */
    public void checkWord(CharSequence letter) {
        // Checks each letter in the word against the user's guessed letter.
        for (int i = 0; i < spellingWord.length(); i++) {
            if (i == 0) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic1.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic1.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 1) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic2.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic2.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 2) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic3.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic3.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 3) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic4.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic4.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 4) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic5.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic5.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 5) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic6.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic6.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 6) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic7.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic7.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 7) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic8.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic8.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 8) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic9.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic9.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 9) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic10.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic10.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 10) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic11.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic11.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 11) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic12.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic12.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            } else if (i == 12) {
                if (("" + spellingWord.charAt(i)).equals(letter)) {
                    letterGraphic13.setImageResource(getResources().getIdentifier(
                            "@drawable/" + letter, null, getPackageName()));
                    letterGraphic13.setContentDescription(letter);
                    replacedLetters++;
                    foundLetter = true;
                }
            }
        }
        Log.i(TAG, replacedLetters + " letters have been replaced after checking.");
        Log.i(TAG, wrongGuesses + " incorrect guesses have been made after checking.");

        // Calls method to check the progress on the game.
        checkProgress();
    }

    /**
     * Checks if the letter was found and calls the appropriate method to process the results.
     */
    public void checkProgress(){
        if(foundLetter == false) {
            wrongGuesses++;
            Log.i(TAG, wrongGuesses + " incorrect guesses have been made (checkProgress).");
            letterNotFound();
        } else {
            letterFound();
            Log.i(TAG, replacedLetters + " letters have been replaced. (checkProgress)");
        }
    }

    /**
     * Updates the shark attack graphic if the user's guessed letter did not appear in the word.
     * If the user has made 7 incorrect guesses, the user loses and is informed on the screen.
     */
    public void letterNotFound() {
        sharkAttack.setImageResource(getResources().getIdentifier("@drawable/shark" +
                (wrongGuesses + 1), null, getPackageName()));

        // After 7 wrong guesses, the user loses.
        if (wrongGuesses == 7){
            sharkAttack.setContentDescription("Shark attack!");
            gameProgressText.setText("SHARK ATTACK! \nThe correct answer was " + correctWord + ".");
            gameProgressText.setBackgroundColor(-65536);
            gameProgressText.setTextColor(-1);
            gameProgressText.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Checks if the user has completed the word and displays the results on the screen if they
     * have.
     */
    public void letterFound() {
        if (replacedLetters == spellingWord.length()){
            gameProgressText.setText("Great job! You escaped the shark by correctly guessing the " +
                    "word " + correctWord);
            gameProgressText.setBackgroundColor(-16711936);
            gameProgressText.setTextColor(-16777216);
            gameProgressText.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Reacts to the user touching the "Next Word" button to advance the game to the next word.
     * Resets appropriate views and calls methods to restart the activity.
     */
    public void onClickNextWord(View view) {
        // Set appropriate views to be hidden for next game.
        gameProgressText.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);

        // Re0enable buttons and clear ArrayList to prepare for next round.
        for (View each : disabledViews){
            each.setEnabled(true);
        }
        disabledViews.clear();

        // Initialize variables for counts
        wrongGuesses = 0;
        replacedLetters = 0;
        foundLetter = false;

        // Set up new word for activity.
        getWord();
        setUpWord();

        Log.i(TAG, "Sink or Sign has been set up.");
    }

    /**
     * Sets the letterGraphics to invisible and resets them with the blank line graphic.
     */
    public void clearWord() {
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

        letterGraphic1.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic2.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic3.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic4.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic5.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic6.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic7.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic8.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic9.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic10.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic11.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic12.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
        letterGraphic13.setImageResource(getResources().getIdentifier("@drawable/line",
                null, getPackageName()));
    }
}

