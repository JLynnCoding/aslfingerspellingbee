package edu.bu.metcs.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.widget.TextView;

public class ASLFingerspellingBeeStatisticsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    // Letter Quiz Stats TextViews
    private TextView gamesPlayedLetterQuizDisplay, gamesWonLetterQuizDisplay,
            gamesLostLetterQuizDisplay, percentWonLetterQuizDisplay;

    // Beginner Spelling Bee Stats TextViews
    private TextView gamesPlayedBeginnerSpellingBeeDisplay, gamesWonBeginnerSpellingBeeDisplay,
            gamesLostBeginnerSpellingBeeDisplay, percentWonBeginnerSpellingBeeDisplay;

    // Intermediate Spelling Bee Stats TextViews

    // Sink or Sign Stats Textviews

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_s_l_fingerspelling_bee_statistics);

        sharedPreferences = getSharedPreferences("Stats", Context.MODE_PRIVATE);

        // Link Views for Letter Quiz Statistics
        gamesPlayedLetterQuizDisplay = (TextView) findViewById
                (R.id.letterquiz_games_played_display);
        gamesWonLetterQuizDisplay = (TextView) findViewById
                (R.id.letterquiz_games_won);
        gamesLostLetterQuizDisplay = (TextView) findViewById
                (R.id.letterquiz_games_lost);
        percentWonLetterQuizDisplay = (TextView) findViewById
                (R.id.letterquiz_percent_correct);

        // Link Views for Beginner Spelling Bee Statistics
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

        // Calculate and Display Statistics for Letter Quiz
        gamesPlayedLetterQuizDisplay.setText("Games Played: " + ((sharedPreferences.
                getInt("gamesWonLetterQuiz", 0) + sharedPreferences.
                getInt("gamesLostLetterQuiz", 0))));
        gamesWonLetterQuizDisplay.setText("Games Won: " + (sharedPreferences.
                getInt("gamesWonLetterQuiz", 0)));
        gamesLostLetterQuizDisplay.setText("Games Lost: " + (sharedPreferences.
                getInt("gamesLostLetterQuiz", 0)));
        percentWonLetterQuizDisplay.setText("Accuracy: " + String.format ("%.2f",
                ((sharedPreferences.getInt("gamesWonLetterQuiz", 0) * 100.00) /
                        ((sharedPreferences.getInt("gamesWonLetterQuiz", 0) +
                                sharedPreferences.getInt("gamesLostLetterQuiz",
                                        0))))) + "%");

        // Calculate and Display Statistics for Beginner Fingerspelling Bee
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

    public void resetStatistics() {
        //Clear all?
    }
}