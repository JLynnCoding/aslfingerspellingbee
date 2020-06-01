/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 1
 * Activity for Alphabet Flashcards.
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class FlashcardActivity extends AppCompatActivity {

    private  Alphabet alphabet;  // Loads class that manages English alphabet
    private ImageView handSignGraphic; // Holds graphic for hand sign
    private TextView englishLetter; // Holds display text that corresponds with hand sign graphic
    private Button answerButton, nextCardButton; // Button objects for advancing
    private Switch letterSwitch;
    private boolean letterFirst;

    /**
     * Initializes needed variables.
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

        letterSwitch.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on)
                {
                    //Do something when Switch button is on/checked
                    letterFirst = true;
                }
                else
                {
                    //Do something when Switch is off/unchecked
                    letterFirst = false;
                }
            }
        }));

        setUpFlashCard();
    }

    /**
     * Advances to English letter that corresponds with shown hand sign.
     */
    public void onClickRevealAnswer(View view) {
        if(letterFirst) { //Letter First Selected
            handSignGraphic.setVisibility(View.VISIBLE);
            englishLetter.setVisibility(View.INVISIBLE);
            answerButton.setVisibility(View.VISIBLE);
            nextCardButton.setVisibility(View.INVISIBLE);
        } else { //Sign First (Default)
            handSignGraphic.setVisibility(View.INVISIBLE);
            englishLetter.setVisibility(View.VISIBLE);
            answerButton.setVisibility(View.INVISIBLE);
            nextCardButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Advances to next flashcard.
     */
    public void onClickNextCard(View view) {
        if(letterFirst){
            handSignGraphic.setVisibility(View.INVISIBLE);
            englishLetter.setVisibility(View.VISIBLE);
            answerButton.setVisibility(View.INVISIBLE);
            nextCardButton.setVisibility(View.VISIBLE);
        } else {
            handSignGraphic.setVisibility(View.VISIBLE);
            englishLetter.setVisibility(View.INVISIBLE);
            answerButton.setVisibility(View.VISIBLE);
            nextCardButton.setVisibility(View.INVISIBLE);
        }
        alphabet.getNextLetter();
        setUpFlashCard();
    }

    /**
     * Sets the image of the current flashcard.
     */
    public void setUpFlashCard() {
        handSignGraphic.setImageResource(getResources().getIdentifier("@drawable/" + alphabet.getCurrentLetter(), null, getPackageName()));
        englishLetter.setText(alphabet.getCurrentLetterDisplay());
    }


}
