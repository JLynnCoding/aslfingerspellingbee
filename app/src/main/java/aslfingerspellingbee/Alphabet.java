/**
 * ASL Fingerspelling Bee
 * Class that creates and manages ArrayList for alphabet and provides data needed for app features.
 */

package aslfingerspellingbee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Alphabet {

    ArrayList<String> alphabet;
    int currentPosition;

    /**
     * Loads alphabet in ArrayList for easy reference and changing of order, as needed by different
     * activities in the application.
     */
    public Alphabet() {
        alphabet = new ArrayList<>();
        String letters = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i<letters.length(); i++) {
            alphabet.add("" + letters.charAt(i));
        }
        currentPosition = 0;
    }

    /**
     * Provides capital and lowercase letter in String to be displayed for English reference with
     * corresponding hand sign.
     */
    public String getLetterDisplay(String letter) {
        return letter.toUpperCase() + letter;
    }

    /**
     * Random letter getting for future features.
     */
    public String getRandomLetter() {
        Random randomNumber = new Random();
        return alphabet.get(randomNumber.nextInt(alphabet.size()));
    }

    /**
     * Allows direct access to letter at certain index for use with image name in activity.
     */
    public String getCurrentLetter() {
        return alphabet.get(currentPosition);
    }

    /**
     * Gets formatted display of the current letter.
     */
    public String getCurrentLetterDisplay() {
        return getLetterDisplay(getCurrentLetter());
    }

    /**
     * Iterates through the ArrayList and returns to beginning when it reaches the end.
     */
    public String getNextLetter() {
        currentPosition++;
        if (currentPosition >= alphabet.size()) {
            currentPosition = 0;
        }
        return getCurrentLetter();
    }

    /**
     * Shuffles alphabet for randomization of order and resets position to 0 so it iterates from
     * the beginning of the shuffled list.
     */
    public void shuffleLetters() {
        Collections.shuffle(alphabet);
        currentPosition = 0;
    }
}
