package edu.bu.metcs.activitylifecycle;

import java.util.ArrayList;
import java.util.Random;

public class Alphabet {

    ArrayList<String> alphabet;
    int currentPosition;

    public Alphabet() {
        alphabet = new ArrayList<>();
        String letters = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i<letters.length(); i++) {
            alphabet.add("" + letters.charAt(i));
        }
        currentPosition = 0;
    }

    public String getLetterDisplay(String letter) {
        return letter.toUpperCase() + letter;
    }

    public String getRandomLetter() {
        Random randomNumber = new Random();
        return alphabet.get(randomNumber.nextInt(alphabet.size()));
    }

    public String getCurrentLetter() {
        return alphabet.get(currentPosition);
    }

    public String getCurrentLetterDisplay() {
        return getLetterDisplay(getCurrentLetter());
    }

    public String getNextLetter() {
        currentPosition++;
        if (currentPosition >= alphabet.size()) {
            currentPosition = 0;
        }
        return getCurrentLetter();
    }

}
