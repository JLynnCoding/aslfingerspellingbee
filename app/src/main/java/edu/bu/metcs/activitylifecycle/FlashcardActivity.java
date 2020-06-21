/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 1
 * Activity for Alphabet Flashcards. Shows sign, then allows user to click "Answer" for the English
 * letter.
 * Project Iteration 2
 * Offers reverse functionality. User can toggle letterSwitch to see the English letter first, then
 * the ASL hand sign for that letter.
 * Project Iteration 3
 * Added shake to shuffle functionality.
 * Project Iteration 5
 * Added SavedInstanceState to make sure flashcards continue from where they left off when the
 * orientation changes or the app is otherwise interrupted.
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class FlashcardActivity extends AppCompatActivity implements AccelerometerListener {

    private  Alphabet alphabet;  // Loads class that manages English alphabet
    private ImageView handSignGraphic; // Holds graphic for hand sign
    private TextView englishLetter; // Holds display text that corresponds with hand sign graphic
    private Button answerButton, nextCardButton; // Button objects for advancing
    private Switch letterSwitch;
    private boolean letterFirst;

    /**
     * Initializes needed variables and checks switch for user preference of letter first or sign
     * first.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        alphabet = new Alphabet();
        handSignGraphic = (ImageView) findViewById(R.id.flashcard);
        englishLetter = (TextView) findViewById(R.id.answerText);
        englishLetter.setVisibility(View.INVISIBLE);
        answerButton = (Button) findViewById(R.id.answerButton);
        nextCardButton = (Button) findViewById(R.id.next_card_button);
        nextCardButton.setVisibility(View.INVISIBLE);
        letterSwitch = (Switch) findViewById(R.id.letterSwitch);

        // Switches between letter and hand sign first
        letterSwitch.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on)
                {
                    letterFirst = true;
                    handSignGraphic.setVisibility(View.INVISIBLE);
                    englishLetter.setVisibility(View.VISIBLE);
                    answerButton.setVisibility(View.VISIBLE);
                    nextCardButton.setVisibility(View.INVISIBLE);
                }
                else
                {
                    letterFirst = false;
                    handSignGraphic.setVisibility(View.VISIBLE);
                    englishLetter.setVisibility(View.INVISIBLE);
                    answerButton.setVisibility(View.VISIBLE);
                    nextCardButton.setVisibility(View.INVISIBLE);
                }
            }
        }));

        setUpFlashCard();
    }

    /**
     * Overrides onResume and adds accelerometer monitoring to enable shuffle on shake
     * functionality.
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (AccelerometerManager.isSupported(this)) {
            AccelerometerManager.startListening(this);
        }
    }

    /**
     * Advances to English letter that corresponds with shown hand sign, or vice versa, if letter
     * first is set.
     */
    public void onClickRevealAnswer(View view) {
        if(letterFirst) { //Letter First Selected
            if(view.getId() == handSignGraphic.getId()) {
                onClickNextCard(view);
            } else {
                handSignGraphic.setVisibility(View.VISIBLE);
                englishLetter.setVisibility(View.INVISIBLE);
                reveal();
            }
        } else { //Sign First (Default)
            handSignGraphic.setVisibility(View.INVISIBLE);
            englishLetter.setVisibility(View.VISIBLE);
            reveal();
        }
    }

    /**
     * Changes visibility of the buttons after answer button is clicked.
     */
    public void reveal() {
        answerButton.setVisibility(View.INVISIBLE);
        nextCardButton.setVisibility(View.VISIBLE);
    }

    /**
     * Advances to next flashcard.
     */
    public void onClickNextCard(View view) {
        if(letterFirst){
            if(view.getId() == englishLetter.getId()){
                onClickRevealAnswer(view);
            } else {
                handSignGraphic.setVisibility(View.INVISIBLE);
                englishLetter.setVisibility(View.VISIBLE);
                advance();
            }
        } else {
            handSignGraphic.setVisibility(View.VISIBLE);
            englishLetter.setVisibility(View.INVISIBLE);
            advance();
        }
    }

    /**
     * Displays appropriate button when advancing to next card.
     */
    public void advance(){
        answerButton.setVisibility(View.VISIBLE);
        nextCardButton.setVisibility(View.INVISIBLE);
        alphabet.getNextLetter();
        setUpFlashCard();
    }

    /**
     * Sets the image of the current flashcard.
     */
    public void setUpFlashCard() {
        handSignGraphic.setImageResource(getResources().getIdentifier("@drawable/" +
                alphabet.getCurrentLetter(), null, getPackageName()));
        handSignGraphic.setContentDescription("" + alphabet.getCurrentLetter());
        englishLetter.setText(alphabet.getCurrentLetterDisplay());
    }

    /**
     * Overrides onAccelerationChanged() method of AccelerometerListener Interface.
     */
    @Override
    public void onAccelerationChanged(float x, float y, float z) {}

    /**
     * Overrides onShake() method of AccelerometerListener Interface and responds to shake by
     * shuffling the order of the flashcards.
     */
    @Override
    public void onShake(float force) {
        // Shuffle alphabet
        alphabet.shuffleLetters();

        //Sets up next flash card
        setUpFlashCard();

        // Notify user
        Toast toast = Toast.makeText(this, "Shuffled!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 370);
        toast.show();
    }

    /**
     * Overrides onStop() method of Activity to stop accelerometer listener when app stopped.
     */
    @Override
    public void onStop() {
        super.onStop();
        // Checks if accelerometer is supported
        if (AccelerometerManager.isListening()) {
            // Stop listening
            AccelerometerManager.stopListening();
        }
    }

    /**
     * Overrides onDestroy() method of Activity to stop accelerometer listener when app stopped.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Checks if accelerometer was supported
        if (AccelerometerManager.isListening()) {
            // Stop listening
            AccelerometerManager.stopListening();
        }
    }
}
