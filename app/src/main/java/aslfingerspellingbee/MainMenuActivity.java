/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 1
 * Activity for Main Menu.
 * Updated Project Iteration 5:
 * Added Sink or Sign activity intent, added Statistics Activity intent, and set up Practice
 * Reminder Notification with AlarmManager.
 */

package aslfingerspellingbee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    /**
     * Creates the Menu and sets up the Practice Reminder Notification with the AlarmManager to
     * remind the user to practice their fingerspelling if they haven't opened the app in 2 days.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main_menu);
        /** # Notification suspended until fixed
         *         PendingIntent pendingIntent = PendingIntent.getBroadcast(MainMenuActivity.this,
         *                 101, intent, 0);
         *         AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
         *         am.setRepeating(am.RTC_WAKEUP, System.currentTimeMillis(), (1000 * 60 * 60 * 48),
         *                 pendingIntent);
         */
        Intent intent = new Intent(MainMenuActivity.this, Receiver.class);

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
     * Starts ASL Beginner Fingerspelling Bee intent.
     */
    public void beginnerSpellingClick(View view) {
        Intent intent = new Intent(this, BeginnerSpellingBeeActivity.class);
        startActivity(intent);
    }

    /**
     * Starts ASL Letter Quiz intent.
     */
    public void letterQuizClick(View view) {
        Intent intent = new Intent(this, LetterQuizActivity.class);
        startActivity(intent);
    }

    /**
     * Starts ASL Intermediate Fingerspelling Bee intent.
     */
    public void intermediateSpellingClick(View view) {
        Intent intent = new Intent(this, IntermediateSpellingBeeActivity.class);
        startActivity(intent);
    }

    /**
     * Starts ASL Sink or Sign intent.
     */
    public void sinkOrSignClick(View view) {
        Intent intent = new Intent(this, SinkOrSignActivity.class);
        startActivity(intent);
    }

    /**
     * Starts Statistics intent.
     */
    public void getStatsClick(View view) {
       Intent intent = new Intent(this, ASLFingerspellingBeeStatisticsActivity.class);
       startActivity(intent);
    }
}
