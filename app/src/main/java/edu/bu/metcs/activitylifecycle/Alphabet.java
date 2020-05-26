package edu.bu.metcs.activitylifecycle;

import java.util.ArrayList;

public class Alphabet {

    ArrayList<String> alphabet;

    public void Alphabet() {
        alphabet = new ArrayList<>();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i<letters.length(); i++) {
            alphabet.add("" + letters.charAt(i));
        }
    };

    public String getLetterDisplay(String letter2) {
        for (String letter : alphabet) {
            return letter.toUpperCase() + letter;
        }
        return letter2;
    }







}
