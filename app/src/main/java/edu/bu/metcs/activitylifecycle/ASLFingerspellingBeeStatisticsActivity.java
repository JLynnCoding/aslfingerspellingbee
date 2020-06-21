package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.widget.TextView;

public class ASLFingerspellingBeeStatisticsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private TextView gamesPlayedBeginnerSpellingBeeDisplay, gamesWonBeginnerSpellingBeeDisplay,
            gamesLostBeginnerSpellingBeeDisplay, percentWonBeginnerSpellingBeeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_s_l_fingerspelling_bee_statistics);

        sharedPreferences = getSharedPreferences("Stats", Context.MODE_PRIVATE);

        gamesPlayedBeginnerSpellingBeeDisplay = (TextView) findViewById
                (R.id.beginnerspellingbee_games_played_display);
        gamesWonBeginnerSpellingBeeDisplay = (TextView) findViewById
                (R.id.beginnerspellingbee_games_won);
        gamesLostBeginnerSpellingBeeDisplay = (TextView) findViewById
                (R.id.beginnerspellingbee_games_lost);
        percentWonBeginnerSpellingBeeDisplay = (TextView) findViewById
                (R.id.beginnerspellingbee_percent_correct);

        displayStatistics();

    }

    public void displayStatistics() {
        gamesPlayedBeginnerSpellingBeeDisplay.setText("Games Played: " + ((sharedPreferences.
                getInt("gamesWonBeginnerSpellingBee", 0) + sharedPreferences.
                getInt("gamesLostBeginnerSpellingBee", 0))));
        gamesWonBeginnerSpellingBeeDisplay.setText("Games Won: " + (sharedPreferences.
                getInt("gamesWonBeginnerSpellingBee", 0)));
        gamesLostBeginnerSpellingBeeDisplay.setText("Games Lost: " + (sharedPreferences.
                getInt("gamesLostBeginnerSpellingBee", 0)));
        percentWonBeginnerSpellingBeeDisplay.setText("Accuracy: " + String.format ("%.2f",
                ((sharedPreferences.getInt("gamesWonBeginnerSpellingBee", 0) * 100.00) /
                        ((sharedPreferences.getInt("gamesWonBeginnerSpellingBee", 0) +
                                sharedPreferences.getInt("gamesLostBeginnerSpellingBee",
                                        0))))) + "%");

    }
}