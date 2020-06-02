package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BeginnerSpellingBeeActivity extends AppCompatActivity {

    private ImageView letterGraphic1, letterGraphic2, letterGraphic3, letterGraphic4,
            letterGraphic5, letterGraphic6, letterGraphic7;
    private BeginnerSpellingBeeWords words;
    private String spellingWord;
    private EditText wordGuessText;
    private Button submitButton, nextWordButton;
    private TextView checkGuessDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beginner_spelling_bee);
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

        words = new BeginnerSpellingBeeWords(this);

        getWord();

        setWord();
    }

    public void getWord() {
        spellingWord = words.getRandomWord().toLowerCase();
    }

    public void setWord() {

        letterGraphic1.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(0), null, getPackageName()));
        letterGraphic2.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(1), null, getPackageName()));
        letterGraphic3.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(2), null, getPackageName()));

        if (spellingWord.length()>3){
            letterGraphic4.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(3), null, getPackageName()));
            letterGraphic4.setVisibility(View.VISIBLE);
        } else {
            letterGraphic4.setVisibility(View.INVISIBLE);
        }
        if (spellingWord.length()>4){
            letterGraphic5.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(4), null, getPackageName()));
            letterGraphic5.setVisibility(View.VISIBLE);
        } else {
            letterGraphic5.setVisibility(View.INVISIBLE);
        }
        if (spellingWord.length()>5){
            letterGraphic6.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(5), null, getPackageName()));
            letterGraphic6.setVisibility(View.VISIBLE);
        } else {
            letterGraphic6.setVisibility(View.INVISIBLE);
        }
        if (spellingWord.length()>6){
            letterGraphic7.setImageResource(getResources().getIdentifier("@drawable/" + spellingWord.charAt(6), null, getPackageName()));
            letterGraphic7.setVisibility(View.VISIBLE);
        } else {
            letterGraphic7.setVisibility(View.INVISIBLE);
        }
    }

    public void onClickSubmit(View view) {
        String guess = wordGuessText.getText().toString().toLowerCase();

        if(guess.equals(spellingWord)) {
            checkGuessDisplay.setText("Correct!");
            checkGuessDisplay.setBackgroundColor(-16711936);
        } else {
            checkGuessDisplay.setText("Incorrect. The correct answer is " + spellingWord);
            checkGuessDisplay.setBackgroundColor(-65536);
        }

        submitButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.VISIBLE);
        nextWordButton.setVisibility(View.VISIBLE);
    }

    public void onClickNextWord(View view) {
        nextWordButton.setVisibility(View.INVISIBLE);
        checkGuessDisplay.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        getWord();
        setWord();
    }



}
