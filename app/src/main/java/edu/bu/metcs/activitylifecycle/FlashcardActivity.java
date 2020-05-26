/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 1
 * Activity for Alphabet Flashcards.
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FlashcardActivity extends AppCompatActivity {

    private  Alphabet alphabet;  // Loads class that manages English alphabet
    private ImageView flashcardGraphic; // Holds graphic for hand sign
    private TextView answerText; // Holds display text that corresponds with hand sign graphic
    private Button answerButton, nextCardButton; // Button objects for advancing

    /**
     * Initializes needed variables.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        alphabet = new Alphabet();
        flashcardGraphic = (ImageView) findViewById(R.id.flashcard);
        answerText = (TextView) findViewById(R.id.answerText);
        answerText.setVisibility(View.INVISIBLE);
        answerButton = (Button) findViewById(R.id.answerButton);
        nextCardButton = (Button) findViewById(R.id.next_card_button);
        nextCardButton.setVisibility(View.GONE);
        setUpFlashCard();
    }

    /**
     * Advances to English letter that corresponds with shown hand sign.
     */
    public void onClickRevealAnswer(View view) {
        flashcardGraphic.setVisibility(View.INVISIBLE);
        answerText.setVisibility(View.VISIBLE);
        answerButton.setVisibility(View.GONE);
        nextCardButton.setVisibility(View.VISIBLE);
    }

    /**
     * Advances to next flashcard.
     */
    public void onClickNextCard(View view) {
        flashcardGraphic.setVisibility(View.VISIBLE);
        answerText.setVisibility(View.INVISIBLE);
        alphabet.getNextLetter();
        setUpFlashCard();
        answerButton.setVisibility(View.VISIBLE);
        nextCardButton.setVisibility(View.GONE);
    }

    /**
     * Sets the image of the current flashcard.
     */
    public void setUpFlashCard() {
        flashcardGraphic.setImageResource(getResources().getIdentifier("@drawable/" + alphabet.getCurrentLetter(), null, getPackageName()));
        answerText.setText(alphabet.getCurrentLetterDisplay());
    }
}
