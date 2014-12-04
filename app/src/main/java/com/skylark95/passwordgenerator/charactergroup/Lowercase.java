package com.skylark95.passwordgenerator.charactergroup;

public class Lowercase implements CharacterGroup {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public char[] getChars() {
        return CHARACTERS.toCharArray();
    }
    
}
