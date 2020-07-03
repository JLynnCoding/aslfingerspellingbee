/**
 *  Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 4
 *  Class for the Intermediate Fingerspelling Bee Activity
 *  Update Project Iteration 5
 *  Added Statistics for storage in SharedPreferences and SavedInstanceState to allow for seamless
 *  recreation of the activity. Added onKeyListener to advance to onClickSubmit when user hits
 *  "Enter" key. Also added SeekBar to allow the user to dynamically adjust the
 *  ViewFlipper speed to make the activity easier or more difficult.
 */

package aslfingerspellingbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.SeekBar;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class IntermediateSpellingBeeActivity extends AppCompatActivity {

    // Variables for Game Stats
    private SharedPreferences sharedPreferences;
    private int gamesWon;
    private int gamesLost;
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
    private InputMethodManager imm;
    // Features for Speed Seeker
    private SeekBar speedSeekBar;
    private int flipSpeed = 1500; // Default speed
    private int slowestFlipSpeed = 500; // Minimum
    private TextView secondSpeedDisplay, slower, faster;

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
        speedSeekBar = (SeekBar) findViewById(R.id.speedSeekBar);
        secondSpeedDisplay = (TextView) findViewById(R.id.numberOfSeconds);
        slower = (TextView) findViewById(R.id.slowerText);
        faster = (TextView) findViewById(R.id.fasterText);

        // Link flipper to layout
        handsign_Flipper = findViewById(R.id.handsign_flipper);
        Log.i(TAG, "Created Flipper.");

        // Create new SpellingWords helper object as a source for words.
        words = new SpellingWords(this);

        // Variable for soft keyboard
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        /*
         * Detects enter being pressed on a hardware keyboard and calls the onClickSubmit() method
         * to proceed with submitting and checking the user's response.
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

        // Sets up listener for SeekBar
        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = 2000 - i;
                flipSpeed = i + slowestFlipSpeed;
                handsign_Flipper.setFlipInterval(flipSpeed);

                //secondSpeedDisplay.setText("" + flipSpeed);

                secondSpeedDisplay.setText(calculateSeconds() + " seconds per sign");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { ;
                handsign_Flipper.setFlipInterval(flipSpeed);
            }
        });

        // Initializes SharedPreferences
        sharedPreferences = getSharedPreferences("Stats", Context.MODE_PRIVATE);

        // Checks if the word was already selected and retrieve from savedInstanceState
        if (savedInstanceState != null) {
            correctWord = savedInstanceState.getString("word", words.getRandomWord());
        } else {
            getWord();
        }

        setWord(); // Sets up new word
        stopFlipper();  // Stops flipper after all letters of the word shown.
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
        handsign_Flipper.setFlipInterval(flipSpeed);
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
        // Loads statistics variables from SharedPreferences
        gamesWon = sharedPreferences.getInt("gamesWonIntermediateSpellingBee", 0);
        gamesLost = sharedPreferences.getInt("gamesLostIntermediateSpellingBee", 0);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

        String guess = wordGuessText.getText().toString().toLowerCase();
        wordGuessText.setEnabled(false);
        speedSeekBar.setVisibility(View.INVISIBLE);
        slower.setVisibility(View.INVISIBLE);
        faster.setVisibility(View.INVISIBLE);
        secondSpeedDisplay.setVisibility(View.INVISIBLE);

        // Checks user response against correct answer and updates statistics.
        if(guess.equals(spellingWord)) {
            checkGuessDisplay.setText("Correct!");
            checkGuessDisplay.setBackgroundColor(-16711936);
            checkGuessDisplay.setTextColor(-16777216);
            gamesWon++;
            sharedPrefEditor.putInt("gamesWonIntermediateSpellingBee", gamesWon);
        } else {
            checkGuessDisplay.setText("Incorrect. The correct answer is " + correctWord + ".");
            checkGuessDisplay.setBackgroundColor(-65536);
            checkGuessDisplay.setTextColor(-1);
            gamesLost++;
            sharedPrefEditor.putInt("gamesLostIntermediateSpellingBee", gamesLost);
        }

        sharedPrefEditor.apply(); // Saves updates in SharedPreferences.

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
        wordGuessText.setEnabled(true);
        speedSeekBar.setVisibility(View.VISIBLE);
        slower.setVisibility(View.VISIBLE);
        faster.setVisibility(View.VISIBLE);
        secondSpeedDisplay.setVisibility(View.VISIBLE);

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

    /**
     * Calculates the speed of the flipper as set by the user via the SeekBar and dynamically
     * updates the display.
     */
    public String calculateSeconds() {
        double seconds = flipSpeed * 1.0 / 1000;
        String formattedSeconds = String.format("%.1f", seconds);
        return formattedSeconds;
    }
}