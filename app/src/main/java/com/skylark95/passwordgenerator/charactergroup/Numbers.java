package com.skylark95.passwordgenerator.charactergroup;

public class Numbers implements CharacterGroup {

    private static final String CHARACTERS = "0123456789";

    @Override
    public char[] getChars() {
        return CHARACTERS.toCharArray();
    }
    
}
