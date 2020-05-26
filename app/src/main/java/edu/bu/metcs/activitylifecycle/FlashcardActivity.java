package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FlashcardActivity extends AppCompatActivity {

    private  Alphabet alphabet;
    private ImageView flashcardHand;
    private TextView answerText;
    private Button answerButton, nextCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        alphabet = new Alphabet();
        flashcardHand = (ImageView) findViewById(R.id.flashcard);
        answerText = (TextView) findViewById(R.id.answerText);
        answerText.setVisibility(View.INVISIBLE);
        answerButton = (Button) findViewById(R.id.answerButton);
        nextCardButton = (Button) findViewById(R.id.next_card_button);
        nextCardButton.setVisibility(View.GONE);
        setUpFlashCard();
    }

    public void onClickRevealAnswer(View view) {
        flashcardHand.setVisibility(View.INVISIBLE);
        answerText.setVisibility(View.VISIBLE);
        answerButton.setVisibility(View.GONE);
        nextCardButton.setVisibility(View.VISIBLE);
    }

    public void onClickNextCard(View view) {
        flashcardHand.setVisibility(View.VISIBLE);
        answerText.setVisibility(View.INVISIBLE);
        alphabet.getNextLetter();
        setUpFlashCard();
        answerButton.setVisibility(View.VISIBLE);
        nextCardButton.setVisibility(View.GONE);
    }

    public void setUpFlashCard() {
        flashcardHand.setImageResource(getResources().getIdentifier("@drawable/" + alphabet.getCurrentLetter(), null, getPackageName()));
        answerText.setText(alphabet.getCurrentLetterDisplay());
    }
}
