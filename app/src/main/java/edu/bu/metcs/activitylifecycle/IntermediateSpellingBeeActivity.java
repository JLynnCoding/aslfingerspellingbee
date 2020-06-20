/**
 *  Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 4
 *  Class for the Intermediate Fingerspelling Bee Activity
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class IntermediateSpellingBeeActivity extends AppCompatActivity {

    // ViewFlipper for slideshow of handsigns
    ViewFlipper handsign_Flipper;

    // Variables for word data
    private SpellingWords words;
    private String correctWord;
    private String spellingWord;

    private String TAG = "trackTag";

    // Buttons and other changing layout elements.
    private EditText wordGuessText;
    private Button submitButton, nextWordButton;
    private TextView checkGuessDisplay;


    /**
     * Initializes variables and sets up initial ViewFlipper.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_spelling_bee);

        // Set views to appropriate variables and adjust visibility
        wordGuessText = (EditText) findViewById(R.id.guessWord);
        submitButton = (Button) findViewById(R.id.submitButton);
        nextWordButton = (Button) findViewById(R.id.nextWordButton);
        nextWordButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay = (TextView) findViewById(R.id.checkAnswerText);
        checkGuessDisplay.setVisibility(View.INVISIBLE);

        // Link flipper to layout
        handsign_Flipper = findViewById(R.id.handsign_flipper);
        Log.i(TAG, "Created Flipper.");

        // Create new SpellingWords helper object as a source for words.
        words = new SpellingWords(this);


        if (savedInstanceState != null) {
            correctWord = savedInstanceState.getString("word", words.getRandomWord());
        } else {
            getWord();
        }

        // Get a new word and set up appropriate flipper
        setWord();

        // Stops flipper after all letters of the word shown.
        stopFlipper();
    }

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
        Log.i(TAG, "Retrieved word: " + correctWord);
    }

    /**
     * Sets the ViewFlipper up to scroll through handsign graphics for all letters of the word.
     */
    public void setWord() {
        spellingWord = correctWord.toLowerCase();

        // Load letter handsign graphics into wordFlipper.
        for (int i = 0; i < spellingWord.length(); i++) {
            wordFlipper("@drawable/" + spellingWord.charAt(i),
                    "" + spellingWord.charAt(i));
            Log.i(TAG, "Image for " + spellingWord.charAt(i));
        }

        wordFlipper("@drawable/guess_the_word", "@string");
    }

    /**
     * Displays and animates the handsign graphics to display the word.
     */
    public void wordFlipper(String imageName, String content) {
        // Create ImageView for handsign graphics and sets content.
        ImageView letterView = new ImageView(this);
        letterView.setBackgroundResource(getResources().getIdentifier(imageName, null, getPackageName()));
        letterView.setContentDescription("" + content);

        // Flipper settings.
        handsign_Flipper.addView(letterView);
        handsign_Flipper.setFlipInterval(1500);
        handsign_Flipper.setAutoStart(true);

        // Set up animation for changing between handsign graphics.
        handsign_Flipper.setInAnimation(this, android.R.anim.slide_in_left);
        handsign_Flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        Log.i(TAG, "Displayed " + content + "with animation.");
    }

    /**
     * Check user's typed answer upon their click of the "Submit" button and displays results.
     */
    public void onClickSubmit(View view) {
        String guess = wordGuessText.getText().toString().toLowerCase();

        // Checks user response against correct answer.
        if(guess.equals(spellingWord)) {
            checkGuessDisplay.setText("Correct!");
            checkGuessDisplay.setBackgroundColor(-16711936);
            checkGuessDisplay.setTextColor(-16777216);
        } else {
            checkGuessDisplay.setText("Incorrect. The correct answer is " + correctWord + ".");
            checkGuessDisplay.setBackgroundColor(-65536);
            checkGuessDisplay.setTextColor(-1);
        }

        // Makes response check and next button visible.
        submitButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.VISIBLE);
        nextWordButton.setVisibility(View.VISIBLE);
    }

    /**
     * Gets a new fingerspelled word upon the user clicking "Next Word".
     */
    public void onClickNextWord(View view) {
        handsign_Flipper.removeAllViews();
        nextWordButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        wordGuessText.setText("");

        // Get a new word and set up appropriate flipper
        getWord();
        setWord();

        // Starts and stops flipper.
        handsign_Flipper.startFlipping();
        stopFlipper();
    }

    /**
     * Sets up listener for animations to stop when the "Guess the Word..." image is displayed,
     * after all of the letters have been shown.
     */
    public void stopFlipper(){
        handsign_Flipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {}
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationEnd(Animation animation) {

                int displayedChild = handsign_Flipper.getDisplayedChild();
                int childCount = handsign_Flipper.getChildCount();

                // Stops when last image reached.
                if (displayedChild == childCount - 1) {
                    handsign_Flipper.stopFlipping();
                }
            }
        });
    }
}