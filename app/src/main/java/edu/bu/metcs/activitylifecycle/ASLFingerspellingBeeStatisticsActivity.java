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
    private TextView gamesPlayedIntermediateSpellingBeeDisplay, gamesWonIntermediateSpellingBeeDisplay,
            gamesLostIntermediateSpellingBeeDisplay, percentWonIntermediateSpellingBeeDisplay;

    // Sink or Sign Stats Textviews
    private TextView gamesPlayedSinkOrSignDisplay, gamesWonSinkOrSignDisplay,
            gamesLostSinkOrSignDisplay, percentWonSinkOrSignDisplay;

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

        // Link Views for Intermediate Spelling Bee Statistics
        gamesPlayedIntermediateSpellingBeeDisplay = (TextView) findViewById
                (R.id.intermediatespellingbee_games_played_display);
        gamesWonIntermediateSpellingBeeDisplay = (TextView) findViewById
                (R.id.intermediatespellingbee_games_won);
        gamesLostIntermediateSpellingBeeDisplay = (TextView) findViewById
                (R.id.intermediatespellingbee_games_lost);
        percentWonIntermediateSpellingBeeDisplay = (TextView) findViewById
                (R.id.intermediatespellingbee_percent_correct);

        // Link Views for Sink or Sign Statistics
        gamesPlayedSinkOrSignDisplay = (TextView) findViewById
                (R.id.sinkorsign_games_played_display);
        gamesWonSinkOrSignDisplay = (TextView) findViewById
                (R.id.sinkorsign_games_won);
        gamesLostSinkOrSignDisplay = (TextView) findViewById
                (R.id.sinkorsign_games_lost);
        percentWonSinkOrSignDisplay = (TextView) findViewById
                (R.id.sinkorsign_percent_correct);

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

        // Calculate and Display Statistics for Intermediate Fingerspelling Bee
        gamesPlayedIntermediateSpellingBeeDisplay.setText("Games Played: " + ((sharedPreferences.
                getInt("gamesWonIntermediateSpellingBee", 0) + sharedPreferences.
                getInt("gamesLostIntermediateSpellingBee", 0))));
        gamesWonIntermediateSpellingBeeDisplay.setText("Games Won: " + (sharedPreferences.
                getInt("gamesWonIntermediateSpellingBee", 0)));
        gamesLostIntermediateSpellingBeeDisplay.setText("Games Lost: " + (sharedPreferences.
                getInt("gamesLostIntermediateSpellingBee", 0)));
        percentWonIntermediateSpellingBeeDisplay.setText("Accuracy: " + String.format ("%.2f",
                ((sharedPreferences.getInt("gamesWonIntermediateSpellingBee", 0) * 100.00) /
                        ((sharedPreferences.getInt("gamesWonIntermediateSpellingBee", 0) +
                                sharedPreferences.getInt("gamesLostIntermediateSpellingBee",
                                        0))))) + "%");

        // Calculate and Display Statistics for Sink or Sign
        gamesPlayedSinkOrSignDisplay.setText("Games Played: " + ((sharedPreferences.
                getInt("gamesWonSinkOrSign", 0) + sharedPreferences.
                getInt("gamesLostSinkOrSign", 0))));
        gamesWonSinkOrSignDisplay.setText("Games Won: " + (sharedPreferences.
                getInt("gamesWonSinkOrSign", 0)));
        gamesLostSinkOrSignDisplay.setText("Games Lost: " + (sharedPreferences.
                getInt("gamesLostSinkOrSign", 0)));
        percentWonSinkOrSignDisplay.setText("Accuracy: " + String.format ("%.2f",
                ((sharedPreferences.getInt("gamesWonSinkOrSign", 0) * 100.00) /
                        ((sharedPreferences.getInt("gamesWonSinkOrSign", 0) +
                                sharedPreferences.getInt("gamesLostSinkOrSign",
                                        0))))) + "%");
    }

    public void resetStatistics() {
        //Clear all?
    }
}