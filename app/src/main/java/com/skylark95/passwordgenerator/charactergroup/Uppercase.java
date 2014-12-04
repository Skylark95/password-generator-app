package com.skylark95.passwordgenerator.charactergroup;

public class Uppercase implements CharacterGroup {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public char[] getChars() {
        return CHARACTERS.toCharArray();
    }

}
