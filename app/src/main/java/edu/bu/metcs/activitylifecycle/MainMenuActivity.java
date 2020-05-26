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

    public void newClick(View view) {
        Intent intent = new Intent(this, AlphabetChartActivity.class);
        startActivity(intent);
    }

    public void flashcardClick(View view) {
        Intent intent = new Intent(this, FlashcardActivity.class);
        startActivity(intent);
    }



}
