/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 1
 * Activity for Main Menu.
 */

package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main_menu);
    }

    /**
     * Starts AlphabetChartActivity intent.
     */
    public void alphabetChartClick(View view) {
        Intent intent = new Intent(this, AlphabetChartActivity.class);
        startActivity(intent);
    }

    /**
     * Starts FlashcardActivity intent.
     */
    public void flashcardClick(View view) {
        Intent intent = new Intent(this, FlashcardActivity.class);
        startActivity(intent);
    }

    /**
     * Starts ASL Beginner Spelling Bee intent.
     */
    public void beginnerSpellingClick(View view) {
        Intent intent = new Intent(this, BeginnerSpellingBeeActivity.class);
        startActivity(intent);
    }

}
